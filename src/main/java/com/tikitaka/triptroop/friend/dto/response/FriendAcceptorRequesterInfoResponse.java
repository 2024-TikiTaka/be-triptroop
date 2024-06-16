package com.tikitaka.triptroop.friend.dto.response;

import com.tikitaka.triptroop.friend.domain.entity.Friend;
import com.tikitaka.triptroop.user.domain.entity.Profile;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@Getter
@RequiredArgsConstructor
public class FriendAcceptorRequesterInfoResponse {
    private final Long friendId;
    private final Long requesterId;
    private final Long acceptorId;
    private final String nickname;
    private final String profileImage;

    public static FriendAcceptorRequesterInfoResponse from(Friend friend, Optional<Profile> profileOpt) {
        String nickname = profileOpt.map(Profile::getNickname).orElse("Unknown");
        String profileImage = profileOpt.map(Profile::getProfileImage).orElse("default-profile.png");

        return new FriendAcceptorRequesterInfoResponse(
                friend.getId(),
                friend.getRequesterId(),
                friend.getAccepterId(),
                nickname,
                profileImage
        );
    }
}
