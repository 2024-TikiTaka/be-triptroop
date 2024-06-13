package com.tikitaka.triptroop.schedule.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@ToString
@RequiredArgsConstructor
public class ScheduleItemUpdateRequest {
    @NotNull
//    private final Long scheduleItemId;

    @NotNull
    private final LocalDate planDate;
    private final String address;
    private final String name;
    @NotNull
    private final String kind;

    @Min(value = 1)
    private final Integer cost;

    @NotBlank
    private final String content;
}
