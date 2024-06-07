package com.tikitaka.triptroop.travel.dto.response;

import com.tikitaka.triptroop.image.dto.response.ImageResponse;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;


@Getter
@RequiredArgsConstructor
public class TravelInfoResponse {
    private final TravelResponse travel;
    private final List<ImageResponse> images;

    public static TravelInfoResponse of(TravelResponse travel, List<ImageResponse> images) {
        return new TravelInfoResponse(travel, images);
    }
}
