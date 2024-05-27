package com.tikitaka.triptroop.schedule.service;


import com.tikitaka.triptroop.common.domain.entity.Area;
import com.tikitaka.triptroop.common.domain.repository.AreaRepository;
import com.tikitaka.triptroop.common.exception.NotFoundException;
import com.tikitaka.triptroop.common.exception.type.ExceptionCode;
import com.tikitaka.triptroop.schedule.domain.entity.Schedule;
import com.tikitaka.triptroop.schedule.domain.entity.ScheduleItem;
import com.tikitaka.triptroop.schedule.domain.repository.ScheduleItemRepository;
import com.tikitaka.triptroop.schedule.domain.repository.ScheduleRepository;
import com.tikitaka.triptroop.schedule.dto.request.ScheduleCreateRequest;
import com.tikitaka.triptroop.schedule.dto.request.ScheduleItemCreateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    private final ScheduleItemRepository scheduleItemRepository;

    private final AreaRepository areaRepository;

    public Long save(ScheduleCreateRequest scheduleRequest, Long userId) {
        Area area = areaRepository.findById(scheduleRequest.getAreaId())
                                  .orElseThrow(() -> new NotFoundException(ExceptionCode.NOT_FOUND_AREA_ID));
        final Schedule newSchedule = Schedule.of(
                scheduleRequest.getTitle(),
                scheduleRequest.getCount(),
                userId,
                area,
                scheduleRequest.getEndDate(),
                scheduleRequest.getStartDate()
        );
        final Schedule schedule = scheduleRepository.save(newSchedule);
        return schedule.getId();
    }

    public void saveItem(ScheduleItemCreateRequest scheduleItemRequest, Long id) {

        final ScheduleItem newItem = ScheduleItem.of(
                id,
                scheduleItemRequest.getContent(),
                scheduleItemRequest.getCost(),
                scheduleItemRequest.getPlanDate(),
                scheduleItemRequest.getPlaceId(),
                scheduleItemRequest.getKind()
        );
        final ScheduleItem scheduleItem = scheduleItemRepository.save(newItem);
    }
}

