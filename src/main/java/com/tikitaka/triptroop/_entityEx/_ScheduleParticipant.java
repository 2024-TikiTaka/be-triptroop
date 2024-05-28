package com.tikitaka.triptroop._entityEx;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "schedule_participants")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class _ScheduleParticipant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "reviewer_id", nullable = false)
    private _User reviewer;

    @ManyToOne
    @JoinColumn(name = "schedule_id", nullable = false)
    private _Schedule schedule;

    private Integer reviewPoint;

    private String reviewContent;

    @Column(nullable = false, columnDefinition = "VARCHAR(10) DEFAULT 'REQUESTED'")
    private String status;

    private String cause;

    @Column(nullable = false, columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdAt;

    private LocalDateTime processedAt;
}
