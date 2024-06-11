package com.tikitaka.triptroop.place.dto.response;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;

@Getter
public class PlaceTravelResponse {

    private final String address;
    private final String name;
    

    @QueryProjection
    public PlaceTravelResponse(String address, String name) {
        this.address = address;
        this.name = name;

    }


}
