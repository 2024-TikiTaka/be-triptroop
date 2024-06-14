package com.tikitaka.triptroop.friend.dto.response;

import com.tikitaka.triptroop.friend.domain.entity.Friend;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class FriendAcceptorRequesterInfoResponse {

    private final Long acceptorId;
    private final Long requesterId;

    public static FriendAcceptorRequesterInfoResponse from(Friend friend, Long currentUserId) {
        Long acceptorId = friend.getAccepterId();
        Long requesterId = friend.getRequesterId();

        // currentUserId와 중복되지 않는 ID를 반환
        return new FriendAcceptorRequesterInfoResponse(
                acceptorId.equals(currentUserId) ? null : acceptorId,
                requesterId.equals(currentUserId) ? null : requesterId
        );
    }
}
