package com.tikitaka.triptroop.travel.dto.response;

import com.tikitaka.triptroop.place.dto.PlaceDto;
import com.tikitaka.triptroop.travel.dto.TravelCommentsDto;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class TravelDetailResponse {

    private Long travelId;

    private Long categoryId;

    private String area;

    private PlaceDto place;

    private Long userId;

    private String title;

    private String content;

    private int views;


    private List<String> images;

    private final List<TravelCommentsDto> travelCommentsDto;
}
