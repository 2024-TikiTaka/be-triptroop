package com.tikitaka.triptroop.companion.domain.entity;

import com.tikitaka.triptroop.common.domain.BaseTimeEntity;
import com.tikitaka.triptroop.companion.domain.type.AgeRestriction;
import com.tikitaka.triptroop.companion.domain.type.GenderRestriction;
import com.tikitaka.triptroop.companion.domain.type.OpenStatus;
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

    @Enumerated(EnumType.STRING) //추가 [companion/domain/type/AgeRestriction.java 이넘 파일 추가]
    private AgeRestriction ageRestriction;

    @Enumerated(EnumType.STRING) //추가  [companion/domain/type/GenderRestriction.java 이넘 파일 추가]
    private GenderRestriction genderRestriction;

    private String title;

    private String content;

    private Integer views;

    @Enumerated(EnumType.STRING)
    private OpenStatus status = OpenStatus.OPEN;

    private Boolean isDeleted = false;

    private LocalDateTime deletedAt;
}
