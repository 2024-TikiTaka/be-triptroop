package com.tikitaka.triptroop.place.dto.response;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class PlaceResponse {

    private final String address;

    private final String name;
}
