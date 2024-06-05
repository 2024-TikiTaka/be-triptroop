package com.tikitaka.triptroop.travel.dto.response;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;


@Getter
public class ImageTravelResponse {
    private final Long imageId;
    private final String fullPath;


    @QueryProjection
    public ImageTravelResponse(Long imageId, String path, String uuid) {
        this.imageId = imageId;
        this.fullPath = path + uuid;

    }

}
