package com.tikitaka.triptroop.travel.dto.response;

import com.tikitaka.triptroop.image.dto.response.ImageResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class TravelDetailResponse {

    private String title;
    private String content;
    //    private Long id;
//    private String commentContent;
    private List<TravelCommentResponse> travelComments;
    //    private List<TravelComment> commentContent;
    private List<ImageResponse> image;
    private String profileImage;
    private String nickname;


    public static TravelDetailResponse of(String title, String content, List<TravelCommentResponse> travelComments, List<ImageResponse> image, String profileImage, String nickname) {

        return new TravelDetailResponse(
                title,
                content,
                travelComments,
//                id,
//                commentContent,
                image,
                profileImage,
                nickname

        );
    }
}