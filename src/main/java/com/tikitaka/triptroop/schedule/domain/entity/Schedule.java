package com.tikitaka.triptroop.schedule.domain.entity;


import com.tikitaka.triptroop.common.domain.entity.Area;
import com.tikitaka.triptroop.common.domain.entity.BaseTimeEntity;
import com.tikitaka.triptroop.common.domain.type.Visibility;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "schedules")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Schedule extends BaseTimeEntity {

    @Id
    @Column(name = "schedule_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    @ManyToOne
    @JoinColumn(name = "area_id")
    private Area area;

    private LocalDate startDate;

    private LocalDate endDate;

    private String title;

    private Integer count;

    @Enumerated(EnumType.STRING)
    private Visibility visibility = Visibility.PUBLIC;

    private Boolean isDeleted = false;

    private LocalDateTime deletedAt;
    private Integer views = 0;

    private Schedule(String title, Integer count, Long userId, Area area, LocalDate endDate, LocalDate startDate) {
        this.title = title;
        this.count = count;
        this.userId = userId;
        this.area = area;
        this.endDate = endDate;
        this.startDate = startDate;
    }

    public static Schedule of(String title, Integer count, Long userId, Area area, LocalDate endDate, LocalDate startDate) {
        return new Schedule(
                title, count, userId, area, endDate, startDate
        );
    }
}
