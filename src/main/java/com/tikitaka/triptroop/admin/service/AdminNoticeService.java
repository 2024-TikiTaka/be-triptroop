package com.tikitaka.triptroop.admin.service;

import com.tikitaka.triptroop.admin.domain.repository.AdminNoticeRepository;
import com.tikitaka.triptroop.admin.dto.response.AdminNoticeDetailResponse;
import com.tikitaka.triptroop.admin.dto.response.AdminNoticeResponse;
import com.tikitaka.triptroop.common.exception.NotFoundException;
import com.tikitaka.triptroop.common.exception.type.ExceptionCode;
import com.tikitaka.triptroop.image.domain.entity.Image;
import com.tikitaka.triptroop.image.domain.repository.ImageRepository;
import com.tikitaka.triptroop.image.dto.response.ImageOriginalResponse;
import com.tikitaka.triptroop.notice.domain.entity.Notice;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class AdminNoticeService {

    private final AdminNoticeRepository adminNoticeRepository;
    private final ImageRepository imageRepository;

    /* 1. 공지 관리 > 공지 목록 조회 */
    @Transactional(readOnly = true)
    public List<AdminNoticeResponse> getNoticeList() {
        List<Notice> notices = adminNoticeRepository.findAll();
        return notices.stream().map(AdminNoticeResponse::from).collect(Collectors.toList());
    }

    /* 2. 공지 관리 > 공지 상세 조회 */
    @Transactional(readOnly = true)
    public AdminNoticeDetailResponse getNoticeDetail(Long noticeId) {
        Notice notice = adminNoticeRepository.findById(noticeId)
                .orElseThrow(() -> new NotFoundException(ExceptionCode.NOT_FOUND_NOTICE));
        
        List<Image> images = imageRepository.findByNoticeId(noticeId);
        List<ImageOriginalResponse> imageOriginalResponses = ImageOriginalResponse.from(images);

        return AdminNoticeDetailResponse.from(notice, imageOriginalResponses);
    }
}
