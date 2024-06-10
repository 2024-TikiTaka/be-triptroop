package com.tikitaka.triptroop.friend.dto.response;

import com.tikitaka.triptroop.friend.domain.entity.Friend;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class FriendResponse {

    private final Long acceptorId;

    public static FriendResponse from(Friend friend) {
        return new FriendResponse(friend.getAccepterId());
    }
}
