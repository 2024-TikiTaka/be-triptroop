package com.tikitaka.triptroop.travellog.domain.entity;

import com.tikitaka.triptroop.common.domain.BaseTimeEntity;
import com.tikitaka.triptroop.common.domain.type.Visibility;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "travel_logs")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TravelLog extends BaseTimeEntity {

    @Id
    @Column(name = "travel_log_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    private Long categoryId;

    private Long areaId;

    private LocalDate startDate;

    private LocalDate endDate;

    private String title;

    @Enumerated(EnumType.STRING)
    private Visibility visibility = Visibility.PRIVATE;

    private Boolean isDeleted = false;

    private Long deleted_at;
}
