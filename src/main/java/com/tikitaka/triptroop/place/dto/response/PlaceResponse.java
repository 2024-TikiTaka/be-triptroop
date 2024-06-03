package com.tikitaka.triptroop.place.dto.response;


import com.tikitaka.triptroop.place.domain.entity.Place;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class PlaceResponse {

    private final String kakaomapId;

    private final String address;

    private final String name;


    public static PlaceResponse from(Place place) {
        return new PlaceResponse(
                place.kakaomapId,
                place.address,
                place.name
        );
    }
}
