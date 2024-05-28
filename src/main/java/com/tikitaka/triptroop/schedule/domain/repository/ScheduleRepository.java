package com.tikitaka.triptroop.schedule.domain.repository;

import com.tikitaka.triptroop.common.domain.type.Visibility;
import com.tikitaka.triptroop.schedule.domain.entity.Schedule;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    // TODO: 일정 전체 조회
    Page<Schedule> findByVisibility(Pageable pageable, Visibility visibility);

    // TODO: 일정 지역순 조회
    Page<Schedule> findByAreaSidoAndVisibility(Pageable pageable, String sido, Visibility visibility);

    // TODO: 일정 검색
    Page<Schedule> findByTitleContainsAndVisibility(Pageable pageable, String title, Visibility visibility);

    // TODO: 일정 좋아요순 조회
//    Page<Schedule> findByLikeIdAndVisibility(Pageable pageable, Long likeId, Visibility visibility);

    // TODO: 일정 조회순 조회
//    Page<Schedule> findByVisibilityOrderByViews(Pageable pageable, Visibility visibility);


}
