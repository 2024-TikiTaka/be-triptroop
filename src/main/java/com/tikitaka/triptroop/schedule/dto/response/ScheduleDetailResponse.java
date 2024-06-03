package com.tikitaka.triptroop.schedule.dto.response;

import com.tikitaka.triptroop.image.dto.response.ImageResponse;
import com.tikitaka.triptroop.user.dto.response.UserProfileResponse;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class ScheduleDetailResponse {
    
    private final String title;
    private final String sido;
    private final Integer count;
    private final LocalDate startDate;
    private final LocalDate endDate;
    private final Integer views;
    private final ImageResponse image;
    private final UserProfileResponse userProfile;
    private final List<ScheduleItemResponse> scheduleItem;
    private final List<ScheduleParticipantsResponse> scheduleParticipant;


    public static ScheduleDetailResponse of(
            String title,
            String sido,
            Integer count,
            LocalDate startDate,
            LocalDate endDate,
            Integer views,
            ImageResponse image,
            UserProfileResponse userProfile,
            List<ScheduleItemResponse> scheduleItem,
            List<ScheduleParticipantsResponse> scheduleParticipant) {

        return new ScheduleDetailResponse(
                title,
                sido,
                count,
                startDate,
                endDate,
                views,
                image,
                userProfile,
                scheduleItem,
                scheduleParticipant
        );
    }
}
