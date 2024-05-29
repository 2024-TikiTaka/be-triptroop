package com.tikitaka.triptroop.schedule.service;


import com.tikitaka.triptroop.common.domain.entity.Area;
import com.tikitaka.triptroop.common.domain.repository.AreaRepository;
import com.tikitaka.triptroop.common.domain.type.Visibility;
import com.tikitaka.triptroop.common.exception.NotFoundException;
import com.tikitaka.triptroop.common.exception.type.ExceptionCode;
import com.tikitaka.triptroop.image.domain.entity.Image;
import com.tikitaka.triptroop.image.domain.repository.ImageRepository;
import com.tikitaka.triptroop.schedule.domain.entity.Schedule;
import com.tikitaka.triptroop.schedule.domain.entity.ScheduleItem;
import com.tikitaka.triptroop.schedule.domain.repository.ScheduleItemRepository;
import com.tikitaka.triptroop.schedule.domain.repository.ScheduleRepository;
import com.tikitaka.triptroop.schedule.domain.repository.ScheduleRepositoryImpl;
import com.tikitaka.triptroop.schedule.dto.request.ScheduleCreateRequest;
import com.tikitaka.triptroop.schedule.dto.request.ScheduleItemCreateRequest;
import com.tikitaka.triptroop.schedule.dto.response.ScheduleResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final ScheduleRepositoryImpl scheduleRepositoryImpl;

    private final ScheduleItemRepository scheduleItemRepository;

    private final AreaRepository areaRepository;
    private final ImageRepository imageRepository;

    private Pageable getPageable(final Integer page, final String sort) {
        return PageRequest.of(page - 1, 10, Sort.by(sort != null ? sort : "id").descending());
    }

    private Pageable getPageable(final Integer page) {
        return getPageable(page, null);
    }


    ;

    //    public Page<ScheduleResponse> findAllSchedules(Integer page, String keyword, String sort, Long area) {
////        Page<Schedule> schedules = null;
//        List<Schedule> schedules = scheduleRepositoryImpl.findSchedulesByKeyword(Visibility.PUBLIC, keyword, sort, area);
//        for (Schedule schedule : schedules) {
//            Long scheduleId = schedule.getId();
//            List<Image> images = imageRepository.findByScheduleId(scheduleId);
//        }
//        return new PageImpl<>(ScheduleResponse.fromList(schedules), getPageable(page, sort), schedules.size());
//    }
    public Page<ScheduleResponse> findAllSchedules(Integer page, String keyword, String sort, Long area) {
        List<Schedule> schedules = scheduleRepositoryImpl.findSchedulesByKeyword(Visibility.PUBLIC, keyword, sort, area);
        List<ScheduleResponse> scheduleResponses = new ArrayList<>();

        for (Schedule schedule : schedules) {
            Long scheduleId = schedule.getId();
            List<Image> images = imageRepository.findByScheduleId(scheduleId);

            // 이미지와 함께 ScheduleResponse를 생성하여 리스트에 추가
            ScheduleResponse scheduleResponse = ScheduleResponse.from(schedule, images);
            scheduleResponses.add(scheduleResponse);
        }

        // Page 객체 생성 및 반환
        return new PageImpl<>(scheduleResponses, getPageable(page, sort), schedules.size());
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

