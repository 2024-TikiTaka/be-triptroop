package com.tikitaka.triptroop.travel.dto;

import com.tikitaka.triptroop.area.domain.entity.Area;
import com.tikitaka.triptroop.category.domain.entity.Category;
import com.tikitaka.triptroop.image.util.FileUploadUtils;
import com.tikitaka.triptroop.place.domain.entity.Place;
import com.tikitaka.triptroop.travel.domain.entity.Travel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class TravelDto {

    private final Long id;
    //    private Long userId;
    private final String categoryName;
    private final String sido;
    private final String placeName;
    private final String placeAddress;
    private final String title;
    private final String content;
    private final List<String> imageList;
    private final List<TravelCommentsDto> comments;


    public static TravelDto from(Travel travel, Category category, Area area, Place place, List<TravelCommentsDto> travelComments) {
        return new TravelDto(
                travel.getId(),
                category.getName(),
                area.getSido(),
                place.getName(),
                place.getAddress(),
                travel.getTitle(),
                travel.getContent(),
                travel.getImages().stream().map(FileUploadUtils::getFullPath).toList(),
                travelComments
        );
    }
}
