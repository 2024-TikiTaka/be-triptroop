package com.tikitaka.triptroop.admin.service;

import com.tikitaka.triptroop.admin.domain.repository.AdminNoticeRepository;
import com.tikitaka.triptroop.admin.dto.request.AdminNoticeRequest;
import com.tikitaka.triptroop.admin.dto.request.AdminNoticeUpdateRequest;
import com.tikitaka.triptroop.admin.dto.response.AdminNoticeDetailResponse;
import com.tikitaka.triptroop.admin.dto.response.AdminNoticeResponse;
import com.tikitaka.triptroop.common.exception.NotFoundException;
import com.tikitaka.triptroop.common.exception.type.ExceptionCode;
import com.tikitaka.triptroop.image.domain.entity.Image;
import com.tikitaka.triptroop.image.domain.repository.ImageRepository;
import com.tikitaka.triptroop.image.domain.type.ImageKind;
import com.tikitaka.triptroop.image.dto.response.ImageOriginalResponse;
import com.tikitaka.triptroop.image.service.ImageService;
import com.tikitaka.triptroop.notice.domain.entity.Notice;
import com.tikitaka.triptroop.notice.domain.type.NoticeKind;
import com.tikitaka.triptroop.notice.domain.type.NoticeStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class AdminNoticeService {

    private final AdminNoticeRepository adminNoticeRepository;
    private final ImageRepository imageRepository;
    private final ImageService imageService;

    /* 1. 공지 관리 > 공지 목록 조회 */
    @Transactional(readOnly = true)
    public List<AdminNoticeResponse> getNoticeList() {
        List<Notice> notices = adminNoticeRepository.findAll();
        return notices.stream().map(AdminNoticeResponse::from).collect(Collectors.toList());
    }

    /* 2. 공지 관리 > 공지 상세 조회 */
    @Transactional(readOnly = true)
    public AdminNoticeDetailResponse getNoticeDetail(final Long noticeId) {
        final Notice notice = adminNoticeRepository.findById(noticeId)
                .orElseThrow(() -> new NotFoundException(ExceptionCode.NOT_FOUND_NOTICE));

        List<Image> images = imageRepository.findByNoticeId(noticeId);
        List<ImageOriginalResponse> imageOriginalResponses = ImageOriginalResponse.from(images);

        return AdminNoticeDetailResponse.from(notice, imageOriginalResponses);
    }

    /* 3. 공지 관리 > 공지 등록 */
    @Transactional
    public Long save(final AdminNoticeRequest adminNoticeRequest, final List<MultipartFile> images) {

        final Notice newNotice = Notice.of(
                NoticeKind.valueOf(adminNoticeRequest.getKind().toString()),
                adminNoticeRequest.getIsRead(),
                adminNoticeRequest.getTitle(),
                adminNoticeRequest.getContent(),
                NoticeStatus.valueOf(adminNoticeRequest.getStatus().toString())
        );

        final Notice notice = adminNoticeRepository.save(newNotice);

        if (imageService.hasValidImages(images)) {
            //이미지 검사 통과 -> 이미지 저장됨
            imageService.saveAll(ImageKind.NOTICE, notice.getId(), images);
        } else {
            // 이미지 검사 불합격 -> 이미지 저장 안되고 글만 저장됨.
        }

        return notice.getId();
    }

    /* 4. 공지 관리 > 공지 수정 */
    @Transactional
    public Long updateNotice(final Long noticeId, final AdminNoticeUpdateRequest adminNoticeUpdateRequest, final List<MultipartFile> images) {
        final Notice notice = adminNoticeRepository.findById(noticeId)
                .orElseThrow(() -> new NotFoundException(ExceptionCode.NOT_FOUND_NOTICE));

        notice.update(
                NoticeKind.valueOf(adminNoticeUpdateRequest.getKind().toString()),
                adminNoticeUpdateRequest.getTitle(),
                adminNoticeUpdateRequest.getContent(),
                NoticeStatus.valueOf(adminNoticeUpdateRequest.getStatus().toString()),
                adminNoticeUpdateRequest.getModifiedAt()
        );

        if (imageService.hasValidImages(images)) {
            //이미지 검사 통과 -> 이미지 저장됨
            imageService.updateAll(ImageKind.NOTICE, noticeId, images);
        } else {
            // 이미지 검사 불합격 -> 이미지 저장 안되고 글만 저장됨.
        }


        return notice.getId();
    }
}
