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

    private List<ScheduleParticipantProfileResponse> participantProfile;
    private ScheduleInformationResponse information;
    private List<ScheduleItemInfoResponse> scheduleItemInfoResponse;


    public ScheduleDetailResponse(List<ScheduleParticipantProfileResponse> participantProfiles, ScheduleInformationResponse scheduleInformation, List<ScheduleItemInfoResponse> scheduleItemInfoResponse) {
        this.participantProfile = participantProfiles;
        this.information = scheduleInformation;
        this.scheduleItemInfoResponse = scheduleItemInfoResponse;
    }

    public static ScheduleDetailResponse of(
            ScheduleInformationResponse scheduleInformation,
            List<ScheduleParticipantProfileResponse> participantProfiles,
            List<ScheduleItemInfoResponse> scheduleItemInfoResponse) {

        return new ScheduleDetailResponse(participantProfiles, scheduleInformation, scheduleItemInfoResponse);
    }


}
