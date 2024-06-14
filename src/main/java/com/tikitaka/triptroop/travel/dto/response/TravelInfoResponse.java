package com.tikitaka.triptroop.travel.dto.response;

import com.tikitaka.triptroop.image.dto.response.ImageResponse;
import com.tikitaka.triptroop.place.dto.response.PlaceTravelResponse;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;


@Getter
@RequiredArgsConstructor
public class TravelInfoResponse {
    private final TravelResponse travels;
    private final List<ImageResponse> images;
    private final PlaceTravelResponse place;

    public static TravelInfoResponse of(TravelResponse travel, List<ImageResponse> images, PlaceTravelResponse place) {
        return new TravelInfoResponse(travel, images, place);
    }
}
