package com.tikitaka.triptroop._entityEx;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "user_reviews")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class _UserReview {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "companion_id", nullable = false)
    private _Companion companion;

    @ManyToOne
    @JoinColumn(name = "reviewer_id", nullable = false)
    private _User reviewer;

    @ManyToOne
    @JoinColumn(name = "reviewed_user_id", nullable = false)
    private _User reviewedUser;

    @Column(nullable = false)
    private Integer reviewPoint;

    @Column(nullable = false, columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdAt;

    private LocalDateTime modifiedAt;

    private LocalDateTime deletedAt;
}
