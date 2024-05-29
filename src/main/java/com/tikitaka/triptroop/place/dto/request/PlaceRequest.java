package com.tikitaka.triptroop.place.dto.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class PlaceRequest {

    private final Long placeId;

    private final String kakaomapId;

    private final String address;

    private final String name;


}
