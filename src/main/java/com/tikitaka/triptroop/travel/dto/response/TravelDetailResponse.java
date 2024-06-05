package com.tikitaka.triptroop.travel.dto.response;

import com.tikitaka.triptroop.image.dto.response.ImageResponse;
import com.tikitaka.triptroop.place.dto.response.PlaceResponse;
import com.tikitaka.triptroop.user.dto.response.UserProfileResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
@AllArgsConstructor
public class TravelDetailResponse {

    private String title;
    private String content;
    private UserProfileResponse writer;
    private PlaceResponse place;
    private List<ImageResponse> image;
    private Page<TravelCommentResponse> travelComments;


    public static TravelDetailResponse of(String title, String content, PlaceResponse place, UserProfileResponse userProfile, List<ImageResponse> image, Page<TravelCommentResponse> travelComments) {

        return new TravelDetailResponse(
                title,
                content,
                userProfile,
                place,
                image,
                travelComments
        );
    }
}