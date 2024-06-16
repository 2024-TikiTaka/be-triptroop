package com.tikitaka.triptroop.admin.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.tikitaka.triptroop.image.dto.response.ImageOriginalResponse;
import com.tikitaka.triptroop.image.util.ImageUtils;
import com.tikitaka.triptroop.notice.domain.entity.Notice;
import com.tikitaka.triptroop.notice.domain.type.NoticeKind;
import com.tikitaka.triptroop.notice.domain.type.NoticeStatus;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@RequiredArgsConstructor
public class AdminNoticeDetailResponse {
    private final Long noticeId;
    private final NoticeKind kind;
    private final boolean isRead;
    private final String title;
    private final String content;
    private final NoticeStatus status;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private final LocalDateTime createdAt;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private final LocalDateTime modifiedAt;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private final LocalDateTime deliveredAt;
    private final List<String> imageNames;

    public static AdminNoticeDetailResponse from(final Notice notice, List<ImageOriginalResponse> imageOriginalResponses) {
        List<String> imageNames = ImageUtils.extractImageInfo(imageOriginalResponses);
        return new AdminNoticeDetailResponse(
                notice.getId(),
                notice.getKind(),
                notice.getIsRead(),
                notice.getTitle(),
                notice.getContent(),
                notice.getStatus(),
                notice.getCreatedAt(),
                notice.getModifiedAt(),
                notice.getDeliveredAt(),
                imageNames
        );
    }
}
