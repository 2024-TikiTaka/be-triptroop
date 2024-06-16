package com.tikitaka.triptroop.admin.dto.response;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.tikitaka.triptroop.image.dto.response.ImageOriginalResponse;
import com.tikitaka.triptroop.image.util.ImageUtils;
import com.tikitaka.triptroop.inquiry.domain.entity.Inquiry;
import com.tikitaka.triptroop.inquiry.domain.type.InquiryKind;
import com.tikitaka.triptroop.user.domain.entity.Profile;
import com.tikitaka.triptroop.user.domain.entity.User;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@RequiredArgsConstructor
public class AdminInquiryDetailResponse {

    private final Long inquiryId;
    private final String email;
    private final String nickname;
    private final InquiryKind inquiryKind;
    private final String content;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private final LocalDateTime createdAt;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private final LocalDateTime processedAt;
    private final String status;
    private final String reply;
    private final List<String> imageNames;

    public static AdminInquiryDetailResponse from(final Inquiry inquiry, User user, List<ImageOriginalResponse> imageOriginalResponses, Profile profile) {
        List<String> imageNames = ImageUtils.extractImageInfo(imageOriginalResponses);

        return new AdminInquiryDetailResponse(
                inquiry.getId(),
                user.getEmail(),
                profile.getNickname(),
                inquiry.getKind(),
                inquiry.getContent(),
                inquiry.getCreatedAt(),
                inquiry.getProcessedAt(),
                inquiry.getProcessedAt() != null ? "PROCESSED" : "UNPROCESSED",
                inquiry.getReply(),
                imageNames
        );
    }

}
