package com.tikitaka.triptroop.place.dto.response;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class PlaceInfoResponse {

    private final PlaceTravelResponse place;

    public static PlaceInfoResponse of(final PlaceTravelResponse place) {
        return new PlaceInfoResponse(place);
    }
}
