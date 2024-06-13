package com.tikitaka.triptroop.friend.service;

import com.tikitaka.triptroop.common.exception.NotFoundException;
import com.tikitaka.triptroop.common.exception.type.ExceptionCode;
import com.tikitaka.triptroop.friend.domain.entity.Friend;
import com.tikitaka.triptroop.friend.domain.repository.FriendRepository;
import com.tikitaka.triptroop.friend.dto.response.FriendAcceptorInfoResponse;
import com.tikitaka.triptroop.friend.dto.response.FriendAcceptorRequesterInfoResponse;
import com.tikitaka.triptroop.user.domain.entity.Profile;
import com.tikitaka.triptroop.user.domain.repository.ProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class FriendService {
    private final FriendRepository friendRepository;
    private final ProfileRepository profileRepository;

    public List<FriendAcceptorRequesterInfoResponse> getAcceptedFriends(Long userId) {
        List<Friend> accepterFriends = friendRepository.findByStatusAndAccepterId("ACCEPTED", userId);
        List<Friend> requesterFriends = friendRepository.findByStatusAndRequesterId("ACCEPTED", userId);

        List<Friend> allFriends = new ArrayList<>();
        allFriends.addAll(accepterFriends);
        allFriends.addAll(requesterFriends);

        return allFriends.stream()
                .map(friend -> {
                    Long friendUserId = friend.getRequesterId().equals(userId) ? friend.getAccepterId() : friend.getRequesterId();
                    Optional<Profile> profileOpt = profileRepository.findByUserId(friendUserId);
                    return FriendAcceptorRequesterInfoResponse.from(friend, profileOpt);
                })
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

    public FriendAcceptorInfoResponse rejectFriend(Long requesterId, Long accepterId) {
        Friend friend = friendRepository.findByRequesterIdAndAccepterIdAndStatus(requesterId, accepterId, "REQUESTED")
                .orElseThrow(() -> new NotFoundException(ExceptionCode.NOT_FOUND_USER));
        friend.reject();
        friendRepository.save(friend);
        return FriendAcceptorInfoResponse.from(friend);
    }

    public void deleteFriend(Long requesterId, Long accepterId) {
        Friend friend = friendRepository.findByRequesterIdAndAccepterIdAndStatus(requesterId, accepterId, "ACCEPTED")
                .orElseThrow(() -> new NotFoundException(ExceptionCode.NOT_FOUND_USER));
        friendRepository.delete(friend);
    }
}



















