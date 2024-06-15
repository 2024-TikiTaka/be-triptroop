package com.tikitaka.triptroop.notice.domain.entity;

import com.tikitaka.triptroop.notice.domain.type.NoticeStatus;
import com.tikitaka.triptroop.notice.domain.type.NoticeType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name = "notices")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class Notice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notice_id")
    private Long noticeId;

    @Column(name = "kind", nullable = false)
    private NoticeType kind;

    @Column(name = "is_read", nullable = false)
    private Integer isRead;
  
    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "delivered_at")
    private LocalDateTime deliveredAt;

    @Column(name = "status")
    private NoticeStatus status;

    public Notice(Long noticeId, NoticeType kind, String title, String content, LocalDateTime createdAt) {
        this.noticeId = noticeId;
        this.kind = kind;
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
    }

    public static Notice of(Long noticeId, NoticeType kind, String title, String content, LocalDateTime createdAt) {
        return new Notice(noticeId, kind, title, content, createdAt);
    }
}
