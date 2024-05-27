package com.tikitaka.triptroop.image.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ImageCreateRequest {

    //    private final Long travelId;
    @NotBlank
    private final String path;

    private final Long scheduleId;

    private final Long travelId;

    @NotBlank
    private final String uuid;

    @NotBlank
    private final String name;

    @NotBlank
    private final String extension;
}
