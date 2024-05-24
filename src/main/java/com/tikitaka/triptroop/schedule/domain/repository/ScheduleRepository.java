package com.tikitaka.triptroop.schedule.domain.repository;

import com.tikitaka.triptroop.schedule.domain.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> { }
