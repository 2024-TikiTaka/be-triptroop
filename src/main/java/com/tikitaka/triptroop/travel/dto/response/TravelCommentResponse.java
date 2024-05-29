package com.tikitaka.triptroop.travel.dto.response;

import com.tikitaka.triptroop.travel.domain.entity.TravelComment;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class TravelCommentResponse {

    private final Long id;
    private final Long userId;
    private final String content;

    public static TravelCommentResponse from(TravelComment comment) {
        return new TravelCommentResponse(
                comment.getId(),
                comment.getUser().getId(),
                comment.getContent()
        );
    }

}
