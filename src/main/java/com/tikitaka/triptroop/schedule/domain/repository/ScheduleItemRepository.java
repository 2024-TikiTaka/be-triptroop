package com.tikitaka.triptroop.schedule.domain.repository;

import com.tikitaka.triptroop.schedule.domain.entity.ScheduleItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleItemRepository extends JpaRepository<ScheduleItem, Long> { }
