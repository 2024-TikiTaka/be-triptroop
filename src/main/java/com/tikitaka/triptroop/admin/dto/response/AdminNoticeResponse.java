package com.tikitaka.triptroop.admin.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.tikitaka.triptroop.notice.domain.entity.Notice;
import com.tikitaka.triptroop.notice.domain.type.NoticeKind;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
public class AdminNoticeResponse {

    private final Long noticeId;
    private final NoticeKind kind;
    private final String title;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private final LocalDateTime createdAt;

    public static AdminNoticeResponse from(final Notice notice) {
        return new AdminNoticeResponse(
                notice.getId(),
                notice.getKind(),
                notice.getTitle(),
                notice.getCreatedAt()
        );
    }
}
