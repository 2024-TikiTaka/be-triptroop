package com.tikitaka.triptroop.inquiry.domain.entity;

import com.tikitaka.triptroop.inquiry.domain.type.InquiryKind;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name = "inquiries")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class Inquiry {

    @Id
    @Column(name = "inquiry_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    @Enumerated(value = EnumType.STRING)
    private InquiryKind kind;

    private String content;

    private String reply;

    @CreatedDate
    private LocalDateTime createdAt;

    private LocalDateTime processedAt;

    public void addReply(String reply) {
        this.reply = reply;
        this.processedAt = LocalDateTime.now();
    }
}
