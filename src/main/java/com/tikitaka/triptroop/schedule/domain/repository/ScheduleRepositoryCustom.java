package com.tikitaka.triptroop.schedule.domain.repository;

import com.tikitaka.triptroop.common.domain.type.Visibility;
import com.tikitaka.triptroop.schedule.domain.entity.Schedule;
import com.tikitaka.triptroop.schedule.dto.response.ScheduleInformationResponse;
import com.tikitaka.triptroop.schedule.dto.response.ScheduleItemInfoResponse;
import com.tikitaka.triptroop.schedule.dto.response.ScheduleParticipantProfileResponse;

import java.util.List;

public interface ScheduleRepositoryCustom {
    List<Schedule> findSchedulesByKeyword(Visibility visibility, String keyword, String sort, Long areaId);

    List<ScheduleParticipantProfileResponse> findParticipantsProfilesByScheduleId(Long scheduleId);

    ScheduleInformationResponse findScheduleById(Long scheduleId);

    List<ScheduleItemInfoResponse> findScheduleItemByScheduleId(Long scheduleItemId);
}
