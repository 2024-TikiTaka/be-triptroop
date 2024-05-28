package com.tikitaka.triptroop.place.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class PlaceRequest {
    @NotBlank
    private final Long placeId;
    @Min(value = 1)
    private final String kakaomapId;
    @NotBlank
    private final String address;
    @NotBlank
    private final String name;


}
