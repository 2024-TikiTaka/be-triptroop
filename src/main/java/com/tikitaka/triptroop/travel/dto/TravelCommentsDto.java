package com.tikitaka.triptroop.travel.dto;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class TravelCommentsDto {

    private final Long travelCommentId;

    private final Long userId;

    private final String content;
}
