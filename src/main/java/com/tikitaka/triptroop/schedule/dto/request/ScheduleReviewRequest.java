package com.tikitaka.triptroop.schedule.dto.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ScheduleReviewRequest {

    private Double reviewPoint;
    private String reviewContent;

}
