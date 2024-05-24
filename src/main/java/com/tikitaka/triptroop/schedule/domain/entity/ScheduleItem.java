package com.tikitaka.triptroop.schedule.domain.entity;

import com.tikitaka.triptroop.common.domain.entity.BaseTimeEntity;
import com.tikitaka.triptroop.schedule.domain.type.ScheduleItemKind;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "schedule_items") // <- ""안에 엔티티 매핑할 테이블 명 적어주세요.
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class ScheduleItem extends BaseTimeEntity { // <- Entity 를 본인의 엔티티 명으로 바꿔 주세요. ( ! 첫문자 대문자임 (ex-Member O / member X) )

    /* 매핑할 필드( 컬럼명 : 카멜케이스 )를 적어주세요. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long scheduleId;
    private Long placeId;
    private LocalDate planDate;
    @Enumerated(EnumType.STRING)
    private ScheduleItemKind kind;
    private int cost;
    private String content;
    private Boolean isDeleted = false;
    private LocalDateTime deletedAt;

    public ScheduleItem(Long id, String content, int cost, LocalDate planDate, Long placeId, ScheduleItemKind
            kind) {
        this.scheduleId = id;
        this.content = content;
        this.cost = cost;
        this.planDate = planDate;
        this.placeId = placeId;
        this.kind = kind;
    }

    public static ScheduleItem of(final Long id, final String content, final int cost, final LocalDate planDate, final Long placeId, final ScheduleItemKind kind) {

        return new ScheduleItem(
                id, content, cost, planDate, placeId, kind
        );
    }
}
