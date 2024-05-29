package com.tikitaka.triptroop.userReview.domain.entity;

import com.tikitaka.triptroop.common.domain.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "user_reviews")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class UserReview extends BaseTimeEntity {

    @Id
    @Column(name = "user_review_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long companionId;

    private Long reviewerId;

    private Long reviewedUserId;

    private Integer reviewPoint;

    private LocalDateTime deletedAt;

}
