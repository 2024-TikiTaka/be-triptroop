package com.tikitaka.triptroop.friend.dto.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class FriendAddRequest {
    private final String nickname;
}