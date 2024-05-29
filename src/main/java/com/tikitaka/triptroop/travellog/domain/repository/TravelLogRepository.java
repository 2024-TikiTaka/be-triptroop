package com.tikitaka.triptroop.travellog.domain.repository;

import com.tikitaka.triptroop.travellog.domain.entity.TravelLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TravelLogRepository extends JpaRepository<TravelLog, Long> { }
