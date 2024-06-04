package com.tikitaka.triptroop.schedule.dto.response;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@JsonPropertyOrder({"information", "participantProfile"})
public class ScheduleDetailResponse {
    //
//    private final String title;
//    private final String sido;
//    private final Integer count;
//    private final LocalDate startDate;
//    private final LocalDate endDate;
//    private final Integer views;
//    private final ImageResponse image;
//    private final UserProfileResponse userProfile;
//    private final List<ScheduleItemResponse> scheduleItem;
//    private final List<ScheduleParticipantsResponse> scheduleParticipant;
//
//
//    public static ScheduleDetailResponse of(
//            String title,
//            String sido,
//            Integer count,
//            LocalDate startDate,
//            LocalDate endDate,
//            Integer views,
//            ImageResponse image,
//            UserProfileResponse userProfile,
//            List<ScheduleItemResponse> scheduleItem,
//            List<ScheduleParticipantsResponse> scheduleParticipant) {
//
//        return new ScheduleDetailResponse(
//                title,
//                sido,
//                count,
//                startDate,
//                endDate,
//                views,
//                image,
//                userProfile,
//                scheduleItem,
//                scheduleParticipant
//        );
//    }
    private List<ScheduleParticipantProfileResponse> participantProfile;
    private ScheduleInformationResponse information;


    public ScheduleDetailResponse(List<ScheduleParticipantProfileResponse> participantProfiles, ScheduleInformationResponse scheduleInformation) {
        this.participantProfile = participantProfiles;
        this.information = scheduleInformation;
    }

    //    public static List<ScheduleDetailResponse> of(
//            List<ScheduleParticipantProfileResponse> participantProfiles,
//            List<ScheduleInformationResponse> scheduleInformations) {
//
//        List<ScheduleDetailResponse> scheduleDetailResponses = new ArrayList<>();
//
//        int size = Math.max(participantProfiles.size(), scheduleInformations.size());
//
//        for (int i = 0; i < size; i++) {
//            ScheduleParticipantProfileResponse participantProfile = i < participantProfiles.size() ? participantProfiles.get(i) : null;
//            ScheduleInformationResponse scheduleInformation = i < scheduleInformations.size() ? scheduleInformations.get(i) : null;
//
//            scheduleDetailResponses.add(new ScheduleDetailResponse(participantProfile, scheduleInformation));
//        }
//
//        return scheduleDetailResponses;
//    }
    public static ScheduleDetailResponse of(
            ScheduleInformationResponse scheduleInformation,
            List<ScheduleParticipantProfileResponse> participantProfiles) {

        return new ScheduleDetailResponse(participantProfiles, scheduleInformation);
    }


//    public static ScheduleDetailResponse of(ScheduleParticipantProfileResponse scheduleParticipantProfileResponse, ScheduleInformationResponse scheduleInformationResponses) {
//        return new ScheduleDetailResponse(
//                scheduleParticipantProfileResponse,
//                scheduleInformationResponses
//        );
//    }

}
