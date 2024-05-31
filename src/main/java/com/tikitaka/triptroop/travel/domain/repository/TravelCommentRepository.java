package com.tikitaka.triptroop.travel.domain.repository;

import com.tikitaka.triptroop.travel.domain.entity.TravelComment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TravelCommentRepository extends JpaRepository<TravelComment, Long> {
    List<TravelComment> findByTravelId(Long travelId);
}
