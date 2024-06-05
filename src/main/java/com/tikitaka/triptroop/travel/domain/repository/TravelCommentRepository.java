package com.tikitaka.triptroop.travel.domain.repository;

import com.tikitaka.triptroop.travel.domain.entity.TravelComment;
import com.tikitaka.triptroop.travel.dto.response.TravelCommentResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TravelCommentRepository extends JpaRepository<TravelComment, Long> {
    /* 여행지소개 상세 조회 댓글조회 */
//    List<TravelComment> findByTravelId(Long travelId);

    /* 댓글 조회 */
    @Query("select new com.tikitaka.triptroop.travel.dto.response.TravelCommentResponse(tc, p)" +
            " from TravelComment tc join User u on tc.userId = u.id" +
            " join Profile p on u.id = p.userId" +
            " where tc.travelId = :travelId"
    )
    Page<TravelCommentResponse> findByTravelId(Pageable pageable, Long travelId);


}
