package com.tikitaka.triptroop.notification.controller;

import com.tikitaka.triptroop.common.dto.response.ApiResponse;
import com.tikitaka.triptroop.notification.domain.entity.Notification;
import com.tikitaka.triptroop.notification.domain.type.NotificationType;
import com.tikitaka.triptroop.notification.dto.request.NotificationCreateRequest;
import com.tikitaka.triptroop.notification.dto.response.NotificationResponse;
import com.tikitaka.triptroop.notification.service.NotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/notif")
@RequiredArgsConstructor
@Slf4j
public class NotificationController {
    private final NotificationService notificationService;

    /* 알림 조회 */
    @GetMapping("/{userId}")
    public List<Notification> getNotifications(@PathVariable Long userId) {
        return notificationService.getNotificationsForUser(userId);
    }

    /* 친구신청 알림저장 */
    @PostMapping("/friend-request")
    public ResponseEntity<ApiResponse> createFriendNotification(@RequestBody NotificationCreateRequest request) {
        notificationService.createNotification(request);
        return ResponseEntity.ok(ApiResponse.success("알림이 전송 되었습니다."));
    }


}
