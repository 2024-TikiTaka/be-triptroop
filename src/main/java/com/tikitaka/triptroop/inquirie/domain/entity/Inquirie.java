package com.tikitaka.triptroop.inquirie.domain.entity;

import com.tikitaka.triptroop.inquirie.domain.type.InquirieKind;
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
public class Inquirie {

    @Id
    @Column(name = "inquiry_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    @Enumerated(value = EnumType.STRING)
    private InquirieKind kind;

    private String content;

    private String reply;

    @CreatedDate
    private LocalDateTime createdAt;

    private LocalDateTime processedAt;
}
