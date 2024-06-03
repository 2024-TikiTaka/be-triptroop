package com.tikitaka.triptroop.travel.dto.response;

import com.tikitaka.triptroop.travel.domain.entity.TravelComment;
import com.tikitaka.triptroop.user.dto.response.UserProfileResponse;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class TravelCommentResponse {

    private final Long id;
    private final String content;
    private final UserProfileResponse userInfo;


    public static TravelCommentResponse from(TravelComment comment, UserProfileResponse userInfo) {
        return new TravelCommentResponse(
                comment.getId(),
                comment.getContent(),
                userInfo
        );
    }

//    public static List<TravelCommentResponse> from(List<TravelComment> comments) {
//        return comments.stream()
//                .map(TravelCommentResponse::from)
//                .collect(Collectors.toList());
//    }

}
