package com.tikitaka.triptroop.travel.dto.response;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class TravelCommentsResponse {

    private final Long userId;
    private final String content;
}
