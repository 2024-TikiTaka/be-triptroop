package com.tikitaka.triptroop.notification.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class NotificationCreateRequest {
    private Long userId;
    private String kind;
}