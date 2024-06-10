package com.tikitaka.triptroop.interest.domain.repository;

import com.tikitaka.triptroop.interest.domain.entity.UserInterest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserInterestRepository extends JpaRepository<UserInterest, Long> {
    void deleteByUserId(Long userId);
}
