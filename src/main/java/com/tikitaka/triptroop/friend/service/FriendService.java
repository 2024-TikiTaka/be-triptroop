package com.tikitaka.triptroop.friend.service;

import com.tikitaka.triptroop.friend.domain.entity.Friend;
import com.tikitaka.triptroop.friend.domain.repository.FriendRepository;
import com.tikitaka.triptroop.friend.dto.response.FriendResponse;
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
    public List<FriendResponse> getFriends(Long userId) {
        List<Friend> friendList = friendRepository.findByStatus("ACCEPTED");
        return friendList.stream()
                .filter(friend -> friend.getAcceptor_id().equals(userId))
                .map(FriendResponse::from)
                .collect(Collectors.toList());
    }

}
