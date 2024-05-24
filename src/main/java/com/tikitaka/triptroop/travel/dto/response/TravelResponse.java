package com.tikitaka.triptroop.travel.dto.response;

import com.tikitaka.triptroop.travel.domain.entity.Travel;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class TravelResponse {

    private final Long id;

    private final String title;

    private final String content;

    private final String images;

    public static TravelResponse from(final Travel travel) {
        return new TravelResponse(
                travel.getId(),
                travel.getTitle(),
                travel.getContent(),
                travel.getImages().toString()
        );
    }
}
