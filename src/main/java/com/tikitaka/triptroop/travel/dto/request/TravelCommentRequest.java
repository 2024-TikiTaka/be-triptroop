package com.tikitaka.triptroop.travel.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class TravelCommentRequest {
    //    @NotNull
//    private final Long userId;
    @NotNull
    private final Long travelId;
    @NotBlank
    private final String content;
}
