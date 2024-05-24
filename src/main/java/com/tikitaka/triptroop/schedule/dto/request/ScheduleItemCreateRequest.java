package com.tikitaka.triptroop.schedule.dto.request;

import com.tikitaka.triptroop.schedule.domain.type.ScheduleItemKind;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@Getter
@RequiredArgsConstructor
public class ScheduleItemCreateRequest {

    @NotNull
    private final Long scheduleId;

    private final Long placeId;

    @NotNull
    private final LocalDate planDate;

    @NotNull
    private final ScheduleItemKind kind;

    @Min(value = 1)
    private final Integer cost;

    @NotBlank
    private final String content;
}

