package com.tikitaka.triptroop.notification.domain.entity;

import com.tikitaka.triptroop.notification.domain.type.NotificationType;
import com.tikitaka.triptroop.notification.dto.request.NotificationCreateRequest;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name = "notifications")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notification_id")
    private Long notificationId;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "kind", nullable = false)
    private NotificationType kind;
  
    @Column(name = "is_read", nullable = false)
    private Integer isRead;

    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime created_at;

    public Notification(Long userId, NotificationType kind, String content) {
        this.userId = userId;
        this.kind = kind;
        this.content = content;
    }

    public static Notification of(Long userId, NotificationType kind, String content) {
        return new Notification(userId, kind, content);
    }
}
