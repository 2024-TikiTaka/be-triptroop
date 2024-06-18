package com.tikitaka.triptroop.schedule.domain.repository;

import com.tikitaka.triptroop.common.domain.type.Visibility;
import com.tikitaka.triptroop.schedule.domain.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    // TODO: 일정 상세 조회
    Optional<Schedule> findByIdAndVisibility(Long scheduleId, Visibility visibility);

    Optional<Schedule> findByUserId(Long userId);

    Boolean existsByUserIdAndId(Long userId, Long scheduleId);

    Long findPlaceIdById(Long scheduleId);

}
