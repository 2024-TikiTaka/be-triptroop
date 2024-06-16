package com.tikitaka.triptroop.admin.dto.request;

import com.tikitaka.triptroop.notice.domain.type.NoticeKind;
import com.tikitaka.triptroop.notice.domain.type.NoticeStatus;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class AdminNoticeRequest {

    private final NoticeKind kind;
    private final Boolean isRead;
    private final String title;
    private final String content;
    private final NoticeStatus status;

}
