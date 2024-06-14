package com.tikitaka.triptroop.admin.dto.response;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.tikitaka.triptroop.inquiry.domain.entity.Inquiry;
import com.tikitaka.triptroop.inquiry.domain.type.InquiryKind;
import com.tikitaka.triptroop.user.domain.entity.Profile;
import com.tikitaka.triptroop.user.domain.entity.User;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
public class AdminInquiryListResponse {

    private final Long inquiryId;
    private final String email;
    private final String nickname;
    private final InquiryKind inquiryKind;
    private final String content;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private final LocalDateTime createdAt;
    private final String status;

    public AdminInquiryListResponse(final Inquiry inquiry, User user, Profile profile) {
        this.inquiryId = inquiry.getId();
        this.email = user.getEmail();
        this.nickname = profile.getNickname();
        this.inquiryKind = inquiry.getKind();
        this.content = inquiry.getContent();
        this.createdAt = inquiry.getCreatedAt();
        this.status = inquiry.getProcessedAt() != null ? "PROCESSED" : "UNPROCESSED";
    }

}
