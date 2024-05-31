package com.tikitaka.triptroop.travel.dto.response;

import com.tikitaka.triptroop.image.dto.response.ImageResponse;
import com.tikitaka.triptroop.place.dto.response.PlaceResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class TravelDetailResponse {

    private String title;
    private String content;
    private List<TravelCommentResponse> travelComments;
    private List<ImageResponse> image;
    private PlaceResponse place;
    private String profileImage;
    private String nickname;


    public static TravelDetailResponse of(String title, String content, List<TravelCommentResponse> travelComments, List<ImageResponse> image, PlaceResponse place, String profileImage, String nickname) {

        return new TravelDetailResponse(
                title,
                content,
                travelComments,
                image,
                place,
                profileImage,
                nickname

        );
    }
}