package com.tikitaka.triptroop.schedule.dto.request;

import com.tikitaka.triptroop.common.domain.type.RequestStatus;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
public class ScheduleParticipantRejectedRequest {

    private RequestStatus status = RequestStatus.REJECTED;

    private String cause;

    private LocalDateTime processedAt = LocalDateTime.now();

}

