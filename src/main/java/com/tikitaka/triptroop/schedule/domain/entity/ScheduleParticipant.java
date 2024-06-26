package com.tikitaka.triptroop.schedule.domain.entity;

import com.tikitaka.triptroop.common.domain.type.RequestStatus;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Entity
@Table(name = "schedule_participants")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SQLDelete(sql = "UPDATE schedule_participants SET review_point = null, review_content = null WHERE schedule_participant_id = ?")
public class ScheduleParticipant {

    @Id
    @Column(name = "schedule_participant_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private Long reviewerId;

    private Long scheduleId;

    private Double reviewPoint;

    private String reviewContent;

    @Enumerated(EnumType.STRING)
    private RequestStatus status = RequestStatus.REQUESTED;

    private String cause;

    @CreatedDate
    private LocalDateTime createdAt = LocalDateTime.now();

    private LocalDateTime processedAt;

    public ScheduleParticipant(Long scheduleId, Long userId, LocalDateTime processedAt) {
        this.scheduleId = scheduleId;
        this.reviewerId = userId;
        this.processedAt = processedAt;
    }

    public ScheduleParticipant(Long userId, Long scheduleId, RequestStatus status) {
        this.scheduleId = scheduleId;
        this.reviewerId = userId;
        this.status = status;
    }


    public static ScheduleParticipant of(Long scheduleId, Long userId, LocalDateTime processedAt) {
        return new ScheduleParticipant(
                scheduleId,
                userId,
                processedAt
        );
    }

    public static ScheduleParticipant saveUser(Long userId, Long scheduleId, RequestStatus status) {
        return new ScheduleParticipant(userId,
                scheduleId,
                status
        );
    }

    public void update(RequestStatus status, LocalDateTime processedAt) {
        this.status = status;
        this.processedAt = processedAt;
    }

    public void rejected(RequestStatus status, LocalDateTime processedAt, String cause) {
        this.status = status;
        this.processedAt = processedAt;
        this.cause = cause;
    }

    public void writeReview(Double reviewPoint, String reviewContent) {
        this.reviewPoint = reviewPoint;
        this.reviewContent = reviewContent;
    }

    public void updateReview(Double reviewPoint, String reviewContent) {
        this.reviewPoint = reviewPoint;
        this.reviewContent = reviewContent;
    }
}
