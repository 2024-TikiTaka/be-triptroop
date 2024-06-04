package com.tikitaka.triptroop.schedule.dto.request;

import com.tikitaka.triptroop.common.domain.type.RequestStatus;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
public class ScheduleParticipantAcceptRequest {

    private RequestStatus status = RequestStatus.ACCEPTED;

    private LocalDateTime processedAt = LocalDateTime.now();

}

