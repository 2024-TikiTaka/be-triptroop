package com.tikitaka.triptroop.friend.domain.repository;

import com.tikitaka.triptroop.friend.domain.entity.Friend;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FriendRepository extends JpaRepository<Friend, Long> {
    List<Friend> findByStatusAndAccepterId(String status, Long accepterId);
}
