package com.tikitaka.triptroop.travel.domain.entity;

import com.tikitaka.triptroop.common.domain.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "travel_comments")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TravelComment extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "travel_comment_id")
    private Long id;

    private Long userId;

    private Long travelId;

    private String content;

    private boolean isDeleted;

    private LocalDateTime deletedAt;
}
