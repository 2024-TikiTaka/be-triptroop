package com.tikitaka.triptroop.travel.domain.repository;

import com.tikitaka.triptroop.travel.domain.entity.TravelComment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TravelCommentRepository extends JpaRepository<TravelComment, Long> {
    /* 여행지소개 상세 조회 댓글조회 */
    List<TravelComment> findByTravelId(Long travelId);

    /* 댓글 조회 */
    Page<TravelComment> findByTravelId(Pageable pageable, Long travelId);


}
