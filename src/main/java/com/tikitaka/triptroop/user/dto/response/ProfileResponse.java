package com.tikitaka.triptroop.user.dto.response;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class ProfileResponse {

    private final Long profileId;
    private final Long userId;
    private final String nickname;
    private final String profileImage;

}
