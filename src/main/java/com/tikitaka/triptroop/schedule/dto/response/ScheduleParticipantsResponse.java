package com.tikitaka.triptroop.schedule.dto.response;

import com.tikitaka.triptroop.schedule.domain.entity.ScheduleParticipant;
import com.tikitaka.triptroop.user.dto.response.UserProfileResponse;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class ScheduleParticipantsResponse {

    private final Long scheduleParticipantId;

    private final String reviewContent;

    private final Double reviewPoint;

    private final UserProfileResponse userInfo;


    public static ScheduleParticipantsResponse from(ScheduleParticipant scheduleParticipant,
                                                    UserProfileResponse userInfo) {
        return new ScheduleParticipantsResponse(
                scheduleParticipant.getId(),
                scheduleParticipant.getReviewContent(),
                scheduleParticipant.getReviewPoint(),
                userInfo
        );
    }

    public static List<ScheduleParticipantsResponse> from(List<ScheduleParticipant> scheduleParticipants, List<UserProfileResponse> userInfos) {
//
//        List<ScheduleParticipantsResponse> scheduleParticipants
//                = scheduleParticipants.stream()
//                .map(ScheduleParticipantsResponse::from)
//                .collect(Collectors.toList());
//    }
        Map<Long, UserProfileResponse> userInfoMap = userInfos.stream()
                .collect(Collectors.toMap(UserProfileResponse::getUserId, userInfo -> userInfo));
        return scheduleParticipants.stream()
                .map(participant -> {
                    UserProfileResponse userProfile = userInfoMap.get(participant.getReviewerId());
                    return ScheduleParticipantsResponse.from(participant, userProfile);
                })
                .collect(Collectors.toList());
    }
}
