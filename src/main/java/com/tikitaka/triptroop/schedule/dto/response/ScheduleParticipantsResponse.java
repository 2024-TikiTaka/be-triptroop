package com.tikitaka.triptroop.schedule.dto.response;

import com.tikitaka.triptroop.schedule.domain.entity.ScheduleParticipant;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class ScheduleParticipantsResponse {
    private Long scheduleParticipantId;

    private Double reviewPoint;

    private String userNickname;

    private String reviewContent;

    public ScheduleParticipantsResponse(Long id, String reviewContent, Double reviewPoint) {

        this.scheduleParticipantId = id;
        this.reviewContent = reviewContent;
        this.reviewPoint = reviewPoint;

    }

    public static ScheduleParticipantsResponse from(ScheduleParticipant scheduleParticipant) {
        return new ScheduleParticipantsResponse(
                scheduleParticipant.getId(),
                scheduleParticipant.getReviewContent(),
                scheduleParticipant.getReviewPoint()
        );
    }

    public static List<ScheduleParticipantsResponse> from(List<ScheduleParticipant> scheduleParticipants) {
        return scheduleParticipants.stream()
                .map(ScheduleParticipantsResponse::from)
                .collect(Collectors.toList());
    }


//    public static ScheduleParticipantsResponse from(ScheduleParticipants scheduleParticipant) {
//        return new ScheduleParticipantsResponse(
//                scheduleParticipant.getId(),
//                scheduleParticipant.getReviewContent(),
//                scheduleParticipant.getReviewPoint()
//        );
//    }
}
