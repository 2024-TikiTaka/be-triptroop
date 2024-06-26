package com.tikitaka.triptroop.user.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.tikitaka.triptroop.user.domain.entity.Profile;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProfileResponse {

    private final Long profileId;

    private final String nickname;

    private final String introduction;

    private final String profileImage;

    private final String mbti;

    public static ProfileResponse from(Profile profile) {
        return new ProfileResponse(
                profile.getId(),
                profile.getNickname(),
                profile.getIntroduction(),
                profile.getProfileImage(),
                profile.getMbti()
        );
    }
}
