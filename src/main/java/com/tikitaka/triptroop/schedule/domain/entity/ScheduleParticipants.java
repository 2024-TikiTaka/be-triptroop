package com.tikitaka.triptroop.schedule.domain.entity;

import com.tikitaka.triptroop.common.domain.BaseTimeEntity;
import com.tikitaka.triptroop.common.domain.type.RequestStatus;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "schedules_participants")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ScheduleParticipants extends BaseTimeEntity {

    @Id
    @Column(name = "schedule_participant_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private long userId;

    @ManyToOne
    @JoinColumn(name = "scheduleId")
    private Schedule schedule;

    private Double reviewPoint;

    private String reviewContent;

    @Enumerated(EnumType.STRING)
    private RequestStatus status = RequestStatus.REQUESTED;

    private String cause;

    private LocalDateTime processedAt;
}
