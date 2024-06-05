package com.tikitaka.triptroop.schedule.dto.request;

import com.tikitaka.triptroop.common.domain.type.RequestStatus;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
public class ScheduleParticipantRequest {

//    private Long scheduleParticipantId;


//    private Long reviewerId;
//
//
//    private Long scheduleId;


    private RequestStatus status = RequestStatus.REQUESTED;

    private LocalDateTime processedAt = LocalDateTime.now();
    private LocalDateTime createdAt = LocalDateTime.now();

}

