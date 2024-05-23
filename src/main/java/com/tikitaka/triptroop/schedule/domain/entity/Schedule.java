package com.tikitaka.triptroop.schedule.domain.entity;

import com.tikitaka.triptroop.common.domain.entity.BaseTimeEntity;
import com.tikitaka.triptroop.common.domain.type.VisibleStatus;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "schedules") // <- ""안에 엔티티 매핑할 테이블 명 적어주세요.
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class Schedule extends BaseTimeEntity { // <- Entity 를 본인의 엔티티 명으로 바꿔 주세요. ( ! 첫문자 대문자임 (ex-Member O / member X) )

    /* 매핑할 필드( 컬럼명 : 카멜케이스 )를 적어주세요. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    @ManyToOne
    @JoinColumn(name = "areaId")
    private Area area;
    private LocalDate startDate;
    private LocalDate endDate;
    private String title;
    private Long count;
    @Enumerated(EnumType.STRING)
    private VisibleStatus visibility = VisibleStatus.PUBLIC;
    private Boolean isDeleted = false;
    private LocalDateTime deletedAt;

    public Schedule(String title, Long count, Long userId, Area area, LocalDate endDate, LocalDate startDate) {
        this.title = title;
        this.count = count;
        this.userId = userId;
        this.area = area;
        this.endDate = endDate;
        this.startDate = startDate;
    }

    public static Schedule of(String title, Long count, Long userId, Area area, LocalDate endDate, LocalDate startDate) {
        return new Schedule(
                title, count, userId, area, endDate, startDate
        );
    }
}
