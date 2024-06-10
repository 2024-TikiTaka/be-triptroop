package com.tikitaka.triptroop.friend.dto.response;

import com.tikitaka.triptroop.friend.domain.entity.Friend;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class FriendAcceptorInfoResponse {

    private final Long acceptorId;

    public static FriendAcceptorInfoResponse from(Friend friend) {
        return new FriendAcceptorInfoResponse(friend.getAccepterId());
    }
}
