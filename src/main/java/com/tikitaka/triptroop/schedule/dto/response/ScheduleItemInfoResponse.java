package com.tikitaka.triptroop.schedule.dto.response;

import com.querydsl.core.annotations.QueryProjection;
import com.tikitaka.triptroop.schedule.domain.type.ScheduleItemKind;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@Getter
@RequiredArgsConstructor
public class ScheduleItemInfoResponse {


    private String placeName;
    private String placeAddress;

    private LocalDate planDate;

    private ScheduleItemKind kind;

    private Integer cost;

    private String content;

    @QueryProjection
    public ScheduleItemInfoResponse(String placeName, String placeAddress, LocalDate planDate, ScheduleItemKind kind, Integer cost, String content) {
        this.placeName = placeName;
        this.placeAddress = placeAddress;
        this.planDate = planDate;
        this.kind = kind;
        this.cost = cost;
        this.content = content;
    }


}
