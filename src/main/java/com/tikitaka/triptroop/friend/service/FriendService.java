package com.tikitaka.triptroop.friend.service;

import com.tikitaka.triptroop.common.exception.NotFoundException;
import com.tikitaka.triptroop.common.exception.type.ExceptionCode;
import com.tikitaka.triptroop.friend.domain.entity.Friend;
import com.tikitaka.triptroop.friend.domain.repository.FriendRepository;
import com.tikitaka.triptroop.friend.dto.response.FriendAcceptorInfoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class FriendService {
    private final FriendRepository friendRepository;
    public List<FriendAcceptorInfoResponse> getAcceptedFriends(Long userId) {
        List<Friend> friendList = friendRepository.findByStatusAndAccepterId("ACCEPTED", userId);
        return friendList.stream()
                .map(FriendAcceptorInfoResponse::from)
                .collect(Collectors.toList());
    }

    public FriendAcceptorInfoResponse requestFriend(Long requesterId, Long accepterId) {
        Friend friend = Friend.of(requesterId, accepterId);
        friendRepository.save(friend);
        return FriendAcceptorInfoResponse.from(friend);
    }

    public FriendAcceptorInfoResponse acceptFriend(Long requesterId, Long accepterId) {
        Friend friend = friendRepository.findByRequesterIdAndAccepterIdAndStatus(requesterId, accepterId, "REQUESTED")
                .orElseThrow(() -> new NotFoundException(ExceptionCode.NOT_FOUND_USER));
        friend.accept();
        friendRepository.save(friend);
        return FriendAcceptorInfoResponse.from(friend);
    }
}



















