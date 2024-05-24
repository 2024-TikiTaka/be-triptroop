package com.tikitaka.triptroop.schedule.domain.entity;

import com.tikitaka.triptroop.common.domain.entity.BaseTimeEntity;
import com.tikitaka.triptroop.schedule.domain.type.ScheduleItemKind;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "schedule_items")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ScheduleItem extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long scheduleId;

    private Long placeId;

    private LocalDate planDate;

    @Enumerated(EnumType.STRING)
    private ScheduleItemKind kind;

    private Integer cost;

    private String content;

    private Boolean isDeleted = false;

    private LocalDateTime deletedAt;

    private ScheduleItem(Long id, String content, Integer cost, LocalDate planDate, Long placeId, ScheduleItemKind
            kind) {
        this.scheduleId = id;
        this.content = content;
        this.cost = cost;
        this.planDate = planDate;
        this.placeId = placeId;
        this.kind = kind;
    }

    public static ScheduleItem of(final Long id, final String content, final Integer cost, final LocalDate planDate, final Long placeId, final ScheduleItemKind kind) {
        return new ScheduleItem(
                id, content, cost, planDate, placeId, kind
        );
    }
}
