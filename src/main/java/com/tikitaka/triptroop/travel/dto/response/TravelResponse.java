package com.tikitaka.triptroop.travel.dto.response;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;

@Getter
public class TravelResponse {

    private final String title;
    private final String content;
    private final String path;
    private final String address;
    private final String name;
    private final String nickname;
    private final String profileImage;
    private final String commentContent;

    //private final List<ImageResponse> images;

    @QueryProjection
    public TravelResponse(String title, String content, String path, String address, String name, String nickname, String profileImage, String commentContent) {
        this.title = title;
        this.content = content;
        this.path = path;
        this.address = address;
        this.name = name;
        this.nickname = nickname;
        this.profileImage = profileImage;
        this.commentContent = commentContent;
    }
}
