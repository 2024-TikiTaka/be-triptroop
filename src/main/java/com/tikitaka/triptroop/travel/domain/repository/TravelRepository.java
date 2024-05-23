package com.tikitaka.triptroop.travel.domain.repository;

import com.tikitaka.triptroop.travel.domain.entity.Travel;
import com.tikitaka.triptroop.common.domain.type.Visibility;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/* Entity에는 Entity명 적기 */
public interface TravelRepository extends JpaRepository<Travel, Long> {

    /* Repository 앞에 부분을 지우고 본인의 기능 폴더명을 적어주세요. */
    /* 공개 게시글 */
    Page<Travel> findByStatus(Pageable pageable, Visibility travelType);
    /* 비공개 게시글 (관리자)*/
    Page<Travel> findByStatusNot(Pageable pageable, Visibility visibility);
    /* 카테고리 기준 조회*/
    Page<Travel> findByCategoryIdAndStatus(Pageable pageable, Long id, Visibility visibility);
    /* 지역별 기준 조회 */
    Page<Travel> findByAreaIdAndStatus(Pageable pageable, Long id, Visibility visibility);

}
