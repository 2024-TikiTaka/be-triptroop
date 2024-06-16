package com.tikitaka.triptroop.notice.domain.entity;

import com.tikitaka.triptroop.notice.domain.type.NoticeKind;
import com.tikitaka.triptroop.notice.domain.type.NoticeStatus;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
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
    private Long id;

    @Enumerated(value = EnumType.STRING)
    private NoticeKind kind;

    private Boolean isRead;
    private String title;
    private String content;

    @Enumerated(value = EnumType.STRING)
    private NoticeStatus status;

    @CreatedDate
    private LocalDateTime createdAt;

    private LocalDateTime modifiedAt;
    private LocalDateTime deliveredAt;

    private Notice(
            NoticeKind kind,
            Boolean isRead,
            String title,
            String content,
            NoticeStatus status
    ) {
        this.kind = kind;
        this.isRead = isRead;
        this.title = title;
        this.content = content;
        this.status = status;
    }

    public static Notice of(
            NoticeKind kind,
            Boolean isRead,
            String title,
            String content,
            NoticeStatus status
    ) {
        return new Notice(
                kind,
                isRead,
                title,
                content,
                status
        );
    }

    public void update(
            NoticeKind kind,
            String title,
            String content,
            NoticeStatus status
    ) {
        this.kind = kind;
        this.title = title;
        this.content = content;
        this.status = status;
    }

}
