package com.tikitaka.triptroop.companions.domain.entity;

import com.tikitaka.triptroop.common.domain.BaseTimeEntity;
import com.tikitaka.triptroop.companions.domain.type.OpenStatus;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "companions")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Companion extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "companion_id")
    private Long id;

    private Long userId;

    private Long scheduleId;

    private String ageRestriction;

    private String genderRestriction;

    private String title;

    private String content;

    private Integer views;

    private OpenStatus status;

    private Boolean isDeleted = false;

    private LocalDateTime deletedAt;

}
