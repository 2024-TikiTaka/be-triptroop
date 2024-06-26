package com.tikitaka.triptroop.travel.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class TravelRequest {

//    @NotNull
//    private final Long userId;

    @Min(value = 1)
    private final Long categoryId;

    @Min(value = 1)
    private final Long areaId;
    private final String address;
    private final String name;

//    @Min(value = 1)
//    private final Long placeId;

    @NotBlank
    private final String title;

    @NotBlank
    private final String content;


}
