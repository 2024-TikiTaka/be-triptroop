package com.tikitaka.triptroop.place.controller;


import com.tikitaka.triptroop.common.dto.response.ApiResponse;
import com.tikitaka.triptroop.place.dto.response.PlaceInfoResponse;
import com.tikitaka.triptroop.place.service.PlaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class PlaceController {

    private final PlaceService placeService;

    @GetMapping("/travels/{travelId}/place")
    public ResponseEntity<ApiResponse> getAllPlaces(
            @PathVariable final Long travelId
    ) {

        PlaceInfoResponse placeResponse = placeService.findPlace(travelId);

        return ResponseEntity.ok(ApiResponse.success(placeResponse));

    }
}
