package com.tikitaka.triptroop.travel.dto.response;

import com.tikitaka.triptroop.image.util.FileUploadUtils;
import com.tikitaka.triptroop.travel.domain.entity.Travel;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class TravelResponse {

    private final Long id;

    private final Long userid;

    private final String title;

    private final String content;

    private final List<String> images;


    public static TravelResponse from(final Travel travel) {
        return new TravelResponse(
                travel.getId(),
                travel.getUserId(),
                travel.getTitle(),
                travel.getContent(),
                travel.getImages().stream().map(FileUploadUtils::getFullPath).toList()


        );


    }
}

