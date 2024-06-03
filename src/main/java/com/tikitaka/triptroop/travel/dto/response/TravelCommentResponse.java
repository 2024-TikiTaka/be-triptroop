package com.tikitaka.triptroop.travel.dto.response;

import com.tikitaka.triptroop.travel.domain.entity.TravelComment;
import com.tikitaka.triptroop.user.domain.entity.Profile;
import lombok.Getter;

@Getter
public class TravelCommentResponse {

    private final Long id;
    private final String content;
    private final String nickname;
    private final String profileImage;

    public TravelCommentResponse(TravelComment travelComment, Profile profile) {
        this.id = travelComment.getId();
        this.content = travelComment.getContent();
        this.nickname = profile.getNickname();
        this.profileImage = profile.getProfileImage();
    }


}
