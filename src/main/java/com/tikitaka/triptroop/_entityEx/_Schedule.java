package com.tikitaka.triptroop._entityEx;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "schedules")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class _Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private _User user;

    @ManyToOne
    @JoinColumn(name = "area_id", nullable = false)
    private _Area area;

    @Column(nullable = false)
    private LocalDate startDate;

    @Column(nullable = false)
    private LocalDate endDate;

    @Column(nullable = false)
    private String title;

    private Integer count;

    @Column(nullable = false, columnDefinition = "INT DEFAULT 0")
    private Integer views;

    @Column(nullable = false, columnDefinition = "VARCHAR(10) DEFAULT 'PUBLIC'")
    private String visibility;

    @Column(nullable = false, columnDefinition = "TINYINT DEFAULT 0")
    private Boolean isDeleted;

    @Column(nullable = false, columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdAt;

    @Column(nullable = false, columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private LocalDateTime modifiedAt;

    private LocalDateTime deletedAt;

    @OneToMany(mappedBy = "schedule")
    private List<_ScheduleItem> scheduleItems;
}
