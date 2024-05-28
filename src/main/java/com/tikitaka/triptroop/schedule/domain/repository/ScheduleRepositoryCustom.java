package com.tikitaka.triptroop.schedule.domain.repository;

import com.tikitaka.triptroop.common.domain.type.Visibility;
import com.tikitaka.triptroop.schedule.domain.entity.Schedule;

import java.util.List;

public interface ScheduleRepositoryCustom {
    List<Schedule> findSchedulesByKeyword(Visibility visibility, String keyword, String sort);
}
