package com.tikitaka.triptroop.travel.dto.response;

import com.tikitaka.triptroop.common.util.FileUploadUtils;
import com.tikitaka.triptroop.travel.domain.entity.Travel;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class TravelsResponse {

    private final Long id;

    private final String title;

    private final String content;

    private final List<String> images;

    public static TravelsResponse from(final Travel travel) {
        return new TravelsResponse(
                travel.getId(),
                travel.getTitle(),
                travel.getContent(),
                travel.getImages().stream().map(FileUploadUtils::getFullPath).toList()
        );
    }
}
