package com.tikitaka.triptroop.schedule.dto.response;

import com.querydsl.core.annotations.QueryProjection;
import com.tikitaka.triptroop.schedule.domain.type.ScheduleItemKind;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@Getter
@RequiredArgsConstructor
public class ScheduleItemInfoResponse {


    private String name;
    private String address;

    private LocalDate planDate;

    private ScheduleItemKind kind;

    private Integer cost;

    private String content;

    @QueryProjection
    public ScheduleItemInfoResponse(String name, String address, LocalDate planDate, ScheduleItemKind kind, Integer cost, String content) {
        this.name = name;
        this.address = address;
        this.planDate = planDate;
        this.kind = kind;
        this.cost = cost;
        this.content = content;

    }


}
