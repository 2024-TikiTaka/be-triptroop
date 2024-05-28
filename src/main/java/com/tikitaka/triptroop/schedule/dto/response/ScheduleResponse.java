package com.tikitaka.triptroop.schedule.dto.response;

import com.tikitaka.triptroop.schedule.domain.entity.Schedule;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class ScheduleResponse {
    private final String sido;
    private final int count;
    private final LocalDate startDate;
    private final LocalDate endDate;
    private final String title;
    private final int views;

    public static ScheduleResponse from(final Schedule schedule) {
        return new ScheduleResponse(
                schedule.getArea().getSido(),
                schedule.getCount(),
                schedule.getStartDate(),
                schedule.getEndDate(),
                schedule.getTitle(),
                schedule.getViews()
        );
    }

    public static List<ScheduleResponse> fromList(List<Schedule> schedules) {
        return schedules.stream()
                .map(ScheduleResponse::from)
                .collect(Collectors.toList());
    }
}
