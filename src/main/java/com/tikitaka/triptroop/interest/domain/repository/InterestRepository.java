package com.tikitaka.triptroop.interest.domain.repository;


import com.tikitaka.triptroop.interest.domain.entity.Interest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InterestRepository extends JpaRepository<Interest, Long> {

    Optional<Interest> findByName(String interestName);
}
