package com.tikitaka.triptroop.schedule.domain.repository;

import com.tikitaka.triptroop.schedule.domain.entity.ScheduleItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ScheduleItemRepository extends JpaRepository<ScheduleItem, Long> {

    List<ScheduleItem> findByScheduleId(Long scheduleId);

    Optional<ScheduleItem> findById(Long scheduleItemId);

//    boolean existsByUserIdAndId(Long scheduleItemId, Long userId);
}
