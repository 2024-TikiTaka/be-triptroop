package com.tikitaka.triptroop.schedule.controller;

import com.tikitaka.triptroop.common.dto.response.ApiResponse;
import com.tikitaka.triptroop.common.page.PageResponse;
import com.tikitaka.triptroop.common.page.Pagination;
import com.tikitaka.triptroop.common.page.PagingButtonInfo;
import com.tikitaka.triptroop.image.domain.type.ImageKind;
import com.tikitaka.triptroop.image.service.ImageService;
import com.tikitaka.triptroop.schedule.dto.request.ScheduleCreateRequest;
import com.tikitaka.triptroop.schedule.dto.request.ScheduleItemCreateRequest;
import com.tikitaka.triptroop.schedule.dto.request.ScheduleItemUpdateRequest;
import com.tikitaka.triptroop.schedule.dto.request.ScheduleUpdateRequest;
import com.tikitaka.triptroop.schedule.dto.response.ScheduleDetailResponse;
import com.tikitaka.triptroop.schedule.dto.response.ScheduleResponse;
import com.tikitaka.triptroop.schedule.service.ScheduleService;
import com.tikitaka.triptroop.user.domain.type.CustomUser;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.net.URI;

@RestController
@RequestMapping("/api/v1/schedules")
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    private final ImageService imageService;

    // TODO 일정 리스트 조회,조건 조회
    @GetMapping()
    public ResponseEntity<PageResponse> findAllSchedules(
            @RequestParam(defaultValue = "1", name = "page") final Integer page,
            @RequestParam(required = false, name = "keyword") final String keyword,
            @RequestParam(required = false, name = "sort") final String sort,
            @RequestParam(required = false, name = "area") final Long area
    ) {
        final Page<ScheduleResponse> schedules = scheduleService.findAllSchedules(page, keyword, sort, area);
        final PagingButtonInfo pagingButtonInfo = Pagination.getPagingButtonInfo(schedules);
        final PageResponse pagingResponse = PageResponse.of(schedules.getContent(), pagingButtonInfo);
        return ResponseEntity.ok(pagingResponse);
    }

    // TODO 일정 상세 조회
    @GetMapping("/{scheduleId}")
    public ResponseEntity<ScheduleDetailResponse> findByScheduleId(@PathVariable(name = "scheduleId") final Long scheduleId) {
//        final ScheduleDetailResponse scheduleDetailResponse =
//                scheduleService.getFindByScheduleId(scheduleId);
        final ScheduleDetailResponse scheduleDetailResponse =
                scheduleService.findByScheduleId(scheduleId);
        return ResponseEntity.ok(scheduleDetailResponse);
    }

    // TODO 일정 등록
    @PostMapping()
    public ResponseEntity<ApiResponse<Void>> saveSchedule(
            @AuthenticationPrincipal CustomUser loginUser,
            @RequestPart @Valid final ScheduleCreateRequest scheduleRequest,
            @RequestPart final MultipartFile image
    ) {
        Long userId = loginUser.getUserId();
        final Long scheduleId = scheduleService.save(scheduleRequest, userId);
        imageService.save(ImageKind.SCHEDULE, scheduleId, image);

        return ResponseEntity.created(URI.create("/api/v1/schedule/" + scheduleId)).build();
    }

    // TODO 일정 수정
    @PutMapping("/{scheduleId}")
    public ResponseEntity<Void> updateSchedule(
            @AuthenticationPrincipal CustomUser loginUser,
            @PathVariable final Long scheduleId,
            @RequestPart @Valid final ScheduleUpdateRequest scheduleUpdateRequest
    ) {
        Long userId = loginUser.getUserId();
        scheduleService.updateSchedule(scheduleId, scheduleUpdateRequest, userId);

        return ResponseEntity.created(URI.create("/api/v1/schedules" + scheduleId)).build();
    }

    // TODO 일정 썸네일 수정
    @PutMapping("/{scheduleId}/thumbnail")
    public ResponseEntity<Void> updateScheduleThumbnail(
            @AuthenticationPrincipal CustomUser loginUser,
            @PathVariable final Long scheduleId,
            @RequestPart final MultipartFile image) {

        Long userId = loginUser.getUserId();
        scheduleService.updateThumbnail(userId, scheduleId, image);

        return ResponseEntity.created(URI.create("/api/v1/schedules" + scheduleId)).build();
    }

    // TODO 일정 삭제
    @DeleteMapping("/{scheduleId}")
    public ResponseEntity<Void> removeSchedule(
            @AuthenticationPrincipal CustomUser loginUser,
            @PathVariable final Long scheduleId) {
        Long userId = loginUser.getUserId();
        scheduleService.removeSchedule(scheduleId, userId);
        return ResponseEntity.noContent().build();

    }

    // TODO 일정 계획 등록
    @PostMapping("/{scheduleId}/item")
    public ResponseEntity<Void> saveItem(@RequestBody @Valid final ScheduleItemCreateRequest scheduleItemRequest,
                                         @PathVariable final Long scheduleId
    ) {

        scheduleService.saveItem(scheduleItemRequest, scheduleId);

        return ResponseEntity.created(URI.create("/api/v1/schedule/" + scheduleId)).build();
    }

    // TODO 일정 계획 수정
    @PutMapping("/{scheduleItemId}/item")
    public ResponseEntity<Void> updateItem(
            @AuthenticationPrincipal CustomUser loginUser,
            @RequestBody @Valid final ScheduleItemUpdateRequest scheduleItemUpdateRequest,
            @PathVariable final Long scheduleItemId
    ) {
        Long userId = loginUser.getUserId();
        scheduleService.updateItem(scheduleItemUpdateRequest, scheduleItemId, userId);

        return ResponseEntity.created(URI.create("/api/v1/schedule/")).build();
    }

    // TODO 일정 계획 삭제
    @DeleteMapping("/{scheduleItemId}/item")
    public ResponseEntity<Void> removeItem(
            @AuthenticationPrincipal CustomUser loginUser,
            @PathVariable final Long scheduleItemId) {
        Long userId = loginUser.getUserId();
        scheduleService.removeItem(scheduleItemId, userId);
        return ResponseEntity.noContent().build();

    }

}
