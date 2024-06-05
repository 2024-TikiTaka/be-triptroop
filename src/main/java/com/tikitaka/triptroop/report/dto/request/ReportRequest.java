package com.tikitaka.triptroop.report.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ReportRequest {

    @NotNull
    private final Long reporterId;

    @NotBlank
    private final String kind;

    @Min(value = 1)
    private final Long scheduleId;

    @Min(value = 1)
    private final Long reporteeId;

    @Min(value = 1)
    private final Long travelId;

    @Min(value = 1)
    private final Long companionId;

    @NotBlank
    private final String type;

    @NotBlank
    private final String content;

    @NotBlank
    private final String status;

}
