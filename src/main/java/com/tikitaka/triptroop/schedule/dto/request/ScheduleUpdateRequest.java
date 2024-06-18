package com.tikitaka.triptroop.schedule.dto.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@Getter
@RequiredArgsConstructor
public class ScheduleUpdateRequest {


    private final Long areaId;

    private final LocalDate startDate;

    private final LocalDate endDate;

    private final String title;

    private final Integer count;

    private final String visibility;
}
