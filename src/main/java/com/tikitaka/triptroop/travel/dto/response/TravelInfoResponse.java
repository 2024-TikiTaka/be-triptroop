package com.tikitaka.triptroop.travel.dto.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;


@Getter
@RequiredArgsConstructor
public class TravelInfoResponse {
    private final List<ImageTravelResponse> imageTravels;
    private final TravelResponse travel;

    public static TravelInfoResponse of(TravelResponse travelResponse, List<ImageTravelResponse> imageTravelResponses) {
        return new TravelInfoResponse(imageTravelResponses, travelResponse);
    }
}
