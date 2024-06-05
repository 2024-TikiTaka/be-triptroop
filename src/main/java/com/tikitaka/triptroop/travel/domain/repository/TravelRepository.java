package com.tikitaka.triptroop.travel.domain.repository;

import com.tikitaka.triptroop.common.domain.type.Visibility;
import com.tikitaka.triptroop.travel.domain.entity.Travel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.Optional;

/* Entity에는 Entity명 적기 */

public interface TravelRepository extends JpaRepository<Travel, Long> {


    Boolean existsByUserIdAndId(Long userId, Long travelId);


    /* Repository 앞에 부분을 지우고 본인의 기능 폴더명을 적어주세요. */
    /* 공개 게시글 */
    @EntityGraph(attributePaths = "images")
    Page<Travel> findByVisibility(Pageable pageable, Visibility visibility);

    /* 공개 게시글 (지역포함) */
    @EntityGraph(attributePaths = "images")
    Page<Travel> findByAreaIdAndVisibility(Pageable pageable, Long Id, Visibility travelType);

    /* 비공개 게시글 (관리자)*/
    Page<Travel> findByVisibilityNot(Pageable pageable, Visibility visibility);

    /* 공개게시글 카테고리 기준 조회*/
    @EntityGraph(attributePaths = "images")
    Page<Travel> findByCategoryIdAndVisibility(Pageable pageable, Long id, Visibility visibility);

    /* 공개게시글 제목 기준 조회*/
    @EntityGraph(attributePaths = "images")
    @Query("SELECT t FROM Travel t WHERE t.title LIKE %:title% AND t.visibility = :visibility")
    Page<Travel> findByTitleAndVisibility(Pageable pageable, String title, Visibility visibility);

    @EntityGraph(attributePaths = "images")
    Page<Travel> findByCreatedAtAndVisibility(Pageable pageable, Date createdAt, Visibility visibility);

    /* 공개 게시글 상세 조회 */

    @EntityGraph(attributePaths = "images")
    Optional<Travel> findByIdAndVisibility(Long id, Visibility visibility);


}
