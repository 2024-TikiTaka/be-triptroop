package com.tikitaka.triptroop.user.dto.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ProfileSaveRequest {

    private final String nickname;

    private final String introduction;

    private final String mbti;
}
