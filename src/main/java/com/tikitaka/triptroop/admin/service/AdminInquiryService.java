package com.tikitaka.triptroop.admin.service;

import com.tikitaka.triptroop.admin.domain.repository.AdminInquiryRepository;
import com.tikitaka.triptroop.admin.dto.request.AdminInquiryReplyRequest;
import com.tikitaka.triptroop.admin.dto.response.AdminInquiryDetailResponse;
import com.tikitaka.triptroop.admin.dto.response.AdminInquiryListResponse;
import com.tikitaka.triptroop.common.exception.NotFoundException;
import com.tikitaka.triptroop.common.exception.type.ExceptionCode;
import com.tikitaka.triptroop.image.domain.entity.Image;
import com.tikitaka.triptroop.image.domain.repository.ImageRepository;
import com.tikitaka.triptroop.image.dto.response.ImageOriginalResponse;
import com.tikitaka.triptroop.inquiry.domain.entity.Inquiry;
import com.tikitaka.triptroop.user.domain.entity.Profile;
import com.tikitaka.triptroop.user.domain.entity.User;
import com.tikitaka.triptroop.user.domain.repository.ProfileRepository;
import com.tikitaka.triptroop.user.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class AdminInquiryService {

    private final AdminInquiryRepository admininquiryRepository;
    private final ImageRepository imageRepository;
    private final UserRepository userRepository;
    private final ProfileRepository profileRepository;

    /* 1. 문의 목록 조회 */
    @Transactional(readOnly = true)
    public List<AdminInquiryListResponse> getInquriryList() {
        return admininquiryRepository.findAdminInquirysAll();
    }

    /* 2. 문의 상세 조회 */
    @Transactional(readOnly = true)
    public AdminInquiryDetailResponse getInquiryDetail(Long inquiryId) {
        Inquiry inquiry = admininquiryRepository.findById(inquiryId)
                .orElseThrow(() -> new NotFoundException(ExceptionCode.NOT_FOUND_INQUIRY));

        User user = userRepository.findById(inquiry.getUserId())
                .orElseThrow(() -> new NotFoundException(ExceptionCode.NOT_FOUND_USER));
        Profile profile = profileRepository.findById(inquiry.getUserId())
                .orElseThrow(() -> new NotFoundException(ExceptionCode.NOT_FOUND_USER_PROFILE));

        List<Image> images = imageRepository.findByInquiryId(inquiryId);
        List<ImageOriginalResponse> imageOriginalResponses = ImageOriginalResponse.from(images);

        return AdminInquiryDetailResponse.from(inquiry, user, imageOriginalResponses, profile);
    }

    /* 3. 답변 등록 */
    @Transactional
    public Inquiry inquiryReplySave(final Long inquiryId, final AdminInquiryReplyRequest adminInquiryReplyRequest, List<MultipartFile> images) {

        Inquiry inquiry = admininquiryRepository.findById(inquiryId)
                .orElseThrow(() -> new NotFoundException(ExceptionCode.NOT_FOUND_INQUIRY));

        inquiry.addReply(adminInquiryReplyRequest.getReply());
        admininquiryRepository.save(inquiry);

        return inquiry;
    }
}
