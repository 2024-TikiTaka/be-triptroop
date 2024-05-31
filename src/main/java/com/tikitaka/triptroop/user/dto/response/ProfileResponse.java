package com.tikitaka.triptroop.user.dto.response;

import com.tikitaka.triptroop.user.domain.entity.Profile;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class ProfileResponse {

    private final Long profileId;

    private final String nickname;

    private final String introduction;

    private final String profileImage;

    private final String mbti;


    public static ProfileResponse of(Profile profile) {
        return new ProfileResponse(
                profile.getProfileId(),
                profile.getNickname(),
                profile.getIntroduction(),
                profile.getProfileImage(),
                profile.getMbti()
        );
    }
}
