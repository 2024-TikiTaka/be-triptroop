package com.tikitaka.triptroop.friend.domain.repository;

import com.tikitaka.triptroop.friend.domain.entity.Friend;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FriendRepository extends JpaRepository<Friend, Long> {

    Optional<Friend> findByRequesterIdAndAccepterIdAndStatus(Long requesterId, Long accepterId, String status);
    List<Friend> findByStatusAndAccepterId(String status, Long accepterId);
    List<Friend> findByStatusAndRequesterId(String status, Long requesterId);
}
