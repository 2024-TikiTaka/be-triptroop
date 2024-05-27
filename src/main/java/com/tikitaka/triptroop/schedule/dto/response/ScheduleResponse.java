package com.tikitaka.triptroop.schedule.dto.response;

import com.tikitaka.triptroop.schedule.domain.entity.Schedule;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class ScheduleResponse {
    private final String sido;
    private final int count;
    private final LocalDate startDate;
    private final LocalDate endDate;
    private final String title;

    public static ScheduleResponse from(final Schedule schedule) {
        return new ScheduleResponse(
                schedule.getArea().getSido(),
                schedule.getCount(),
                schedule.getStartDate(),
                schedule.getEndDate(),
                schedule.getTitle()
        );
    }
}
