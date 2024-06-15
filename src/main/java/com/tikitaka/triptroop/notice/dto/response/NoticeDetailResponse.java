package com.tikitaka.triptroop.notice.dto.response;

import com.tikitaka.triptroop.notice.domain.entity.Notice;
import com.tikitaka.triptroop.notice.domain.type.NoticeType;

import java.time.LocalDateTime;

public class NoticeDetailResponse {
    private Long noticeId;
    private NoticeType kind;
    private String title;
    private String content;
    private LocalDateTime createdAt;

    public NoticeDetailResponse(Long noticeId, NoticeType kind, String title, String content, LocalDateTime createdAt) {
    }

    public static NoticeDetailResponse from(Notice notice) {
        return new NoticeDetailResponse(
                notice.getNoticeId(),
                notice.getKind(),
                notice.getTitle(),
                notice.getContent(),
                notice.getCreatedAt()
        );
    }
}
