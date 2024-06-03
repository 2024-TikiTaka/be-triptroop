package com.tikitaka.triptroop.schedule.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@Getter
@RequiredArgsConstructor
public class ScheduleUpdateRequest {

    @NotNull
    private final Long userId;

    @NotNull
    private final Long areaId;

    @NotNull
    private final LocalDate startDate;

    @NotNull
    private final LocalDate endDate;

    @NotBlank
    private final String title;

    @Min(value = 1)
    private final Integer count;
}
