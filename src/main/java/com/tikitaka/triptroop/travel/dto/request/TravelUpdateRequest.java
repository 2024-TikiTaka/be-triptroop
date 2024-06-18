package com.tikitaka.triptroop.travel.dto.request;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@RequiredArgsConstructor
public class TravelUpdateRequest {

//    @NotNull
//    private final Long userId;

    @Min(value = 1)
    private final Long categoryId;

    @Min(value = 1)
    private final Long areaId;

    //    @Min(value = 1)
//    private final Long placeId;
    private final String address;
    private final String name;

    @NotBlank
    private final String title;

    @NotBlank
    private final String content;

    @NotBlank
    private final String status;
}
