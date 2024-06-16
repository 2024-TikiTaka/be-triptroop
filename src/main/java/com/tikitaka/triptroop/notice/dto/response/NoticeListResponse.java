package com.tikitaka.triptroop.notice.dto.response;

import com.tikitaka.triptroop.notice.domain.entity.Notice;

import java.time.LocalDateTime;

public class NoticeListResponse {
    private Long noticeId;
    private String title;
    private LocalDateTime createdAt;

    public NoticeListResponse(Long noticeId, String title, LocalDateTime createdAt) {
    }

    public static NoticeListResponse from(Notice notice) {
        return new NoticeListResponse(
                notice.getId(),
                notice.getTitle(),
                notice.getCreatedAt()
        );
    }
}
