package com.tikitaka.triptroop.schedule.service;


import com.tikitaka.triptroop.common.domain.entity.Area;
import com.tikitaka.triptroop.common.domain.repository.AreaRepository;
import com.tikitaka.triptroop.common.domain.type.Visibility;
import com.tikitaka.triptroop.common.exception.NotFoundException;
import com.tikitaka.triptroop.common.exception.type.ExceptionCode;
import com.tikitaka.triptroop.schedule.domain.entity.Schedule;
import com.tikitaka.triptroop.schedule.domain.entity.ScheduleItem;
import com.tikitaka.triptroop.schedule.domain.repository.ScheduleItemRepository;
import com.tikitaka.triptroop.schedule.domain.repository.ScheduleRepository;
import com.tikitaka.triptroop.schedule.dto.request.ScheduleCreateRequest;
import com.tikitaka.triptroop.schedule.dto.request.ScheduleItemCreateRequest;
import com.tikitaka.triptroop.schedule.dto.response.ScheduleResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    private final ScheduleItemRepository scheduleItemRepository;

    private final AreaRepository areaRepository;

    private Pageable getPageable(final Integer page) {
        return PageRequest.of(page - 1, 10, Sort.by("id").descending());
    }

    ;

    public Page<ScheduleResponse> findAllSchedules(Integer page, String sido, String title, Integer views) {
        Page<Schedule> schedules = null;
//        Page<Like> likes = null;

        if (sido != null && !sido.isEmpty()) {
            schedules = scheduleRepository.findByAreaSidoAndVisibility(getPageable(page), sido, Visibility.PUBLIC);
        } else if (title != null && !title.isEmpty()) {
            schedules = scheduleRepository.findByTitleContainsAndVisibility(getPageable(page), title, Visibility.PUBLIC);
//        } else if (likeId != null && likeId > 0) {
//            schedules = scheduleRepository.findByLikeIdAndVisibility(getPageable(page), likeId, Visibility.PUBLIC);

        } else if (views != null && views > 0) {
            schedules = scheduleRepository.findByViewsAndVisibility(getPageable(page), views, Visibility.PUBLIC);
        } else {
            schedules = scheduleRepository.findByVisibility(getPageable(page), Visibility.PUBLIC);
        }
        return schedules.map(ScheduleResponse::from);
    }

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

    public Long saveItem(ScheduleItemCreateRequest scheduleItemRequest, Long id) {
        final ScheduleItem newItem = ScheduleItem.of(
                id,
                scheduleItemRequest.getContent(),
                scheduleItemRequest.getCost(),
                scheduleItemRequest.getPlanDate(),
                scheduleItemRequest.getKind()
        );
        final ScheduleItem scheduleItem = scheduleItemRepository.save(newItem);
        return scheduleItem.getId();
    }

}

