package com.tikitaka.triptroop.travel.dto.response;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class TravelResponse {

//    private final Long id;
//    //    private Long userId;
//    private final String categoryName;
//    private final String sido;
//    private final String placeName;
//    private final String placeAddress;
//    private final String title;
//    private final String content;
//    private final List<String> imageList;
//    private final List<TravelCommentResponse> comments;
//
//
//    public static TravelResponse from(Travel travel, Category category, Area area, Place place, List<TravelComment> travelComments) {
//        return new TravelResponse(
//                travel.getId(),
//                category.getName(),
//                area.getSido(),
//                place.getName(),
//                place.getAddress(),
//                travel.getTitle(),
//                travel.getContent(),
//                travel.getImages().stream().map(FileUploadUtils::getFullPath).toList(),
//                travelComments.stream().map(TravelCommentResponse::from).toList()
//        );
//    }
}

