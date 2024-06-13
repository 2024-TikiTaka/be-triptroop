package com.tikitaka.triptroop.notification.service;

import com.tikitaka.triptroop.notification.domain.entity.Notification;
import com.tikitaka.triptroop.notification.domain.repository.NotificationRepository;
import com.tikitaka.triptroop.notification.domain.type.NotificationType;
import com.tikitaka.triptroop.notification.dto.request.NotificationCreateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
@RequiredArgsConstructor
public class NotificationService {
    private final NotificationRepository notificationRepository;

    public List<Notification> getNotificationsForUser(Long userId) {
        return notificationRepository.findByUserId(userId);
    }

    public void createNotification(NotificationCreateRequest request) {
        NotificationType kind = NotificationType.valueOf(request.getKind());
        String content = generateContent(kind);
        Notification notification = Notification.of(request.getUserId(), kind, content);
        notificationRepository.save(notification);
    }
    private String generateContent(NotificationType kind) {
        switch (kind) {
            case LIKE:
                return "여행이 좋아요를 받았습니다.";
            case FRIEND_REQUESTED:
                return "새 친구 요청이 있습니다.";
            case SCHEDULE_ACCEPTED:
                return "일정 요청이 승인되었습니다.";
            case SCHEDULE_REJECTED:
                return "일정 요청이 거절되었습니다.";
            case REPORT_COMPLETED:
                return "신고가 처리되었습니다.";
            case QNA:
                return "새로운 질문이 있습니다.";
            default:
                throw new IllegalArgumentException("Unknown notification type: " + kind);
        }
    }


}



















