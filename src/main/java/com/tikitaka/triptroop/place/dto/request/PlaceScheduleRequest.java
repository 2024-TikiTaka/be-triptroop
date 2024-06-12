package com.tikitaka.triptroop.place.dto.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class PlaceScheduleRequest {

    private final String address;

    private final String name;


}
