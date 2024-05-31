package com.tikitaka.triptroop.travel.dto.response;

import com.tikitaka.triptroop.travel.domain.entity.TravelComment;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class TravelCommentResponse {

    private final Long id;
    private final Long userId;
    private final String content;

    public static TravelCommentResponse from(TravelComment comment) {
        return new TravelCommentResponse(
                comment.getId(),
                comment.getUserId().getId(),
                comment.getContent()
        );
    }

    public static List<TravelCommentResponse> from(List<TravelComment> comments) {
        return comments.stream()
                .map(TravelCommentResponse::from)
                .collect(Collectors.toList());
    }

}
