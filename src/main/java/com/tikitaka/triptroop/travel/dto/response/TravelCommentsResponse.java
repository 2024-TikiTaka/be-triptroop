package com.tikitaka.triptroop.travel.dto.response;

import com.tikitaka.triptroop.travel.domain.entity.TravelComment;
import com.tikitaka.triptroop.user.domain.entity.Profile;
import com.tikitaka.triptroop.user.dto.response.UserProfileResponse;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class TravelCommentsResponse {

    private final Long id;
    private final UserProfileResponse userInfo;
    private final String content;
    private final String profileImage;
    private final String nickname;

    public static TravelCommentsResponse from(TravelComment travelComment, UserProfileResponse userInfo, Profile profile) {
        return new TravelCommentsResponse(
                travelComment.getId(),
                userInfo,
                travelComment.getContent(),
                profile.getProfileImage(),
                profile.getNickname()

        );
    }


}
