package com.tikitaka.triptroop.schedule.service;


import com.tikitaka.triptroop.area.domain.entity.Area;
import com.tikitaka.triptroop.area.repository.AreaRepository;
import com.tikitaka.triptroop.common.domain.type.Visibility;
import com.tikitaka.triptroop.common.exception.ForbiddenException;
import com.tikitaka.triptroop.common.exception.NotFoundException;
import com.tikitaka.triptroop.common.exception.type.ExceptionCode;
import com.tikitaka.triptroop.image.domain.entity.Image;
import com.tikitaka.triptroop.image.domain.repository.ImageRepository;
import com.tikitaka.triptroop.image.domain.type.ImageKind;
import com.tikitaka.triptroop.image.service.ImageService;
import com.tikitaka.triptroop.schedule.domain.entity.Schedule;
import com.tikitaka.triptroop.schedule.domain.entity.ScheduleItem;
import com.tikitaka.triptroop.schedule.domain.repository.ScheduleItemRepository;
import com.tikitaka.triptroop.schedule.domain.repository.ScheduleParticipantRepository;
import com.tikitaka.triptroop.schedule.domain.repository.ScheduleRepository;
import com.tikitaka.triptroop.schedule.domain.repository.ScheduleRepositoryImpl;
import com.tikitaka.triptroop.schedule.dto.request.ScheduleCreateRequest;
import com.tikitaka.triptroop.schedule.dto.request.ScheduleItemCreateRequest;
import com.tikitaka.triptroop.schedule.dto.request.ScheduleItemUpdateRequest;
import com.tikitaka.triptroop.schedule.dto.request.ScheduleUpdateRequest;
import com.tikitaka.triptroop.schedule.dto.response.*;
import com.tikitaka.triptroop.user.service.ProfileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    private final ScheduleRepositoryImpl scheduleRepositoryImpl;

    private final ScheduleParticipantRepository scheduleParticipantRepository;

    private final ScheduleItemRepository scheduleItemRepository;

    private final AreaRepository areaRepository;

    private final ImageRepository imageRepository;
    private final ImageService imageService;

    private final ProfileService profileService;

    private Pageable getPageable(final Integer page, final String sort) {
        return PageRequest.of(page - 1, 10, Sort.by(sort != null ? sort : "id").descending());
    }

    private Pageable getPageable(final Integer page) {
        return getPageable(page, null);
    }

    // TODO : 전체 리스트 조회
    @Transactional(readOnly = true)
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

    // TODO : 상세 조회
    @Transactional(readOnly = true)
    public ScheduleDetailResponse findByScheduleId(Long scheduleId) {
        List<ScheduleParticipantProfileResponse> scheduleParticipantProfileResponse = scheduleRepositoryImpl.findParticipantsProfilesByScheduleId(scheduleId);
        ScheduleInformationResponse scheduleInformationResponses = scheduleRepositoryImpl.findScheduleById(scheduleId);
        List<ScheduleItemInfoResponse> scheduleItemInfoResponse = scheduleRepositoryImpl.findScheduleItemByScheduleId(scheduleId);
//        Schedule schedule = scheduleRepository.findByIdAndVisibility(scheduleId, Visibility.PUBLIC)
//                .orElseThrow(() -> new NotFoundException(ExceptionCode.NOT_FOUND_SCHEDULE));
//
//        Image image = imageRepository.findById(scheduleId)
//                .orElseThrow(() -> new NotFoundException(ExceptionCode.NOT_FOUND_IMAGE));
//
//        List<ScheduleItem> scheduleItems = scheduleItemRepository.findByScheduleId(scheduleId);
//        List<ScheduleItemResponse> scheduleItem = ScheduleItemResponse.from(scheduleItems);
//        UserProfileResponse userProfile = profileService.findUserProfileByUserId(schedule.getUserId());
//
//        List<ScheduleParticipant> scheduleParticipants = scheduleParticipantRepository.findByScheduleId(scheduleId);
//        List<UserProfileResponse> userInfos = getReviewerProfilesByScheduleId(scheduleId);
//        List<ScheduleParticipantsResponse> scheduleParticipant = ScheduleParticipantsResponse.from(scheduleParticipants, userInfos);
//        ScheduleDetailResponse scheduleDetailResponse = ScheduleDetailResponse.of(
//                schedule.getTitle(),
//                schedule.getArea().getSido(),
//                schedule.getCount(),
//                schedule.getStartDate(),
//                schedule.getEndDate(),
//                schedule.getViews(),
//                ImageResponse.from(image),
//                userProfile,
//                scheduleItem,
//                scheduleParticipant
//
//        );
//
//        return scheduleDetailResponse;
        ScheduleDetailResponse scheduleDetailResponses = ScheduleDetailResponse.of(
                scheduleInformationResponses,
                scheduleParticipantProfileResponse,
                scheduleItemInfoResponse
        );
        return scheduleDetailResponses;
    }


    // TODO : 일정 등록
    public Long save(ScheduleCreateRequest scheduleRequest, Long userId) {
        Area area = areaRepository.findById(scheduleRequest.getAreaId())
                .orElseThrow(() -> new NotFoundException(ExceptionCode.NOT_FOUND_AREA));
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

    // TODO 일정 수정
    public void updateSchedule(Long scheduleId, ScheduleUpdateRequest scheduleUpdateRequest, Long userId) {
        Schedule schedule = scheduleRepository.findByIdAndVisibility(scheduleId, Visibility.PUBLIC).orElseThrow(() -> new NotFoundException(ExceptionCode.NOT_FOUND_SCHEDULE));
        if (!scheduleRepository.existsByUserIdAndId(userId, scheduleId)) {
            throw new ForbiddenException(ExceptionCode.ACCESS_DENIED_POST);
        }

        Area area = areaRepository.findById(scheduleUpdateRequest.getAreaId())
                .orElseThrow(() -> new NotFoundException(ExceptionCode.NOT_FOUND_AREA));


        schedule.update(
                scheduleUpdateRequest.getTitle(),
                scheduleUpdateRequest.getCount(),
                area,
                scheduleUpdateRequest.getEndDate(),
                scheduleUpdateRequest.getStartDate()
        );
    }

    // TODO 일정 썸네일 수정
    public void updateThumbnail(Long userId, Long scheduleId, MultipartFile image) {
        if (!scheduleRepository.existsByUserIdAndId(userId, scheduleId)) {
            throw new ForbiddenException(ExceptionCode.ACCESS_DENIED);
        }

        imageService.updateImage(ImageKind.SCHEDULE, scheduleId, image);
    }


    // TODO 일정 공개 여부 변경
    public void changeStatus(Long scheduleId, Long userId, String status) {
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(() -> new NotFoundException(ExceptionCode.NOT_FOUND_SCHEDULE));
        if (!scheduleRepository.existsByUserIdAndId(userId, scheduleId)) {
            throw new ForbiddenException(ExceptionCode.ACCESS_DENIED_POST);
        }
        switch (status) {
            case "PUBLIC":
                schedule.changeStatus(Visibility.PUBLIC);
                break;
            case "PRIVATE":
                schedule.changeStatus(Visibility.PRIVATE);
                break;
        }

    }

    // TODO 일정 삭제
    public void removeSchedule(Long scheduleId, Long userId) {
        if (!scheduleRepository.existsByUserIdAndId(scheduleId, userId)) {
            throw new ForbiddenException(ExceptionCode.ACCESS_DENIED);
        }
        scheduleRepository.deleteById(scheduleId);
    }

    // TODO : 일정 계획 등록
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

    // TODO 일정 계획 수정
    public void updateItem(ScheduleItemUpdateRequest scheduleItemUpdateRequests, Long scheduleItemId, Long userId) {
//        if (!scheduleItemRepository.existsByUserIdAndId(scheduleItemId, userId)) {
//            throw new ForbiddenException(ExceptionCode.ACCESS_DENIED_POST);
//        }
        ScheduleItem scheduleItems = scheduleItemRepository.findById(scheduleItemId).orElseThrow(() -> new NotFoundException(ExceptionCode.NOT_FOUND_SCHEDULE_ITEM));
        scheduleItems.update(
                scheduleItemUpdateRequests.getContent(),
                scheduleItemUpdateRequests.getCost(),
                scheduleItemUpdateRequests.getKind(),
                scheduleItemUpdateRequests.getPlanDate()
        );

    }

    // TODO 일정 계획 삭제
    public void removeItem(Long scheduleItemId, Long userId) {
//        if (!scheduleItemRepository.existsByUserIdAndId(scheduleItemId, userId)) {
//            throw new ForbiddenException(ExceptionCode.ACCESS_DENIED);
//        }
        scheduleItemRepository.deleteById(scheduleItemId);

    }


//
//    public List<Long> getReviewerIds(List<ScheduleParticipant> scheduleParticipants) {
//        return scheduleParticipants.stream()
//                .map(ScheduleParticipant::getReviewerId)
//                .collect(Collectors.toList());
//    }
//
//    public List<UserProfileResponse> getReviewerProfilesByScheduleId(Long scheduleId) {
//        List<ScheduleParticipant> scheduleParticipants = scheduleParticipantRepository.findByScheduleId(scheduleId);
//        List<Long> reviewerIds = getReviewerIds(scheduleParticipants);
//        return profileService.findByUserIdIn(reviewerIds);
//    }

}

