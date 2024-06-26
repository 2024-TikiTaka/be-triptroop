package com.tikitaka.triptroop.travel.dto.response;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;

@Getter
public class TravelResponse {

    private final String title;
    private final String content;
    private final String address;
    private final String name;
    private final String nickname;
    private final String profileImage;


    //private final List<ImageResponse> images;

    @QueryProjection
    public TravelResponse(String title, String content, String address, String name, String nickname, String profileImage) {
        this.title = title;
        this.content = content;
        this.address = address;
        this.name = name;
        this.nickname = nickname;
        this.profileImage = profileImage;

    }
}
