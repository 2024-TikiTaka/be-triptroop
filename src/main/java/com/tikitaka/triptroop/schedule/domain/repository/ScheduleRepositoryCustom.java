package com.tikitaka.triptroop.schedule.domain.repository;

import com.tikitaka.triptroop.common.domain.type.Visibility;
import com.tikitaka.triptroop.schedule.domain.entity.Schedule;
import com.tikitaka.triptroop.schedule.dto.response.ScheduleInformationResponse;
import com.tikitaka.triptroop.schedule.dto.response.ScheduleItemInfoResponse;
import com.tikitaka.triptroop.schedule.dto.response.ScheduleParticipantProfileResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ScheduleRepositoryCustom {
    Page<Schedule> findSchedulesByKeyword(Pageable pageable, Visibility visibility, String keyword, String sort, Long areaId, boolean deleted);

    List<ScheduleParticipantProfileResponse> findParticipantsProfilesByScheduleId(Long scheduleId);

    ScheduleInformationResponse findScheduleById(Long scheduleId);

    List<ScheduleItemInfoResponse> findScheduleItemByScheduleId(Long scheduleItemId);
}
