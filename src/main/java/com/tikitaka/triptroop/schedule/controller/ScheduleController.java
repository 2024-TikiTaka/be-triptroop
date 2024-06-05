package com.tikitaka.triptroop.schedule.controller;

import com.tikitaka.triptroop.common.dto.response.ApiResponse;
import com.tikitaka.triptroop.common.page.PageResponse;
import com.tikitaka.triptroop.common.page.Pagination;
import com.tikitaka.triptroop.common.page.PagingButtonInfo;
import com.tikitaka.triptroop.image.domain.type.ImageKind;
import com.tikitaka.triptroop.image.service.ImageService;
import com.tikitaka.triptroop.schedule.dto.request.*;
import com.tikitaka.triptroop.schedule.dto.response.ScheduleDetailResponse;
import com.tikitaka.triptroop.schedule.dto.response.ScheduleResponse;
import com.tikitaka.triptroop.schedule.service.ScheduleParticipantService;
import com.tikitaka.triptroop.schedule.service.ScheduleService;
import com.tikitaka.triptroop.user.domain.type.CustomUser;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
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
    private final ScheduleParticipantService scheduleParticipantService;

    private final ImageService imageService;

    // TODO 일정 리스트 조회,조건 조회
    @GetMapping()
    public ResponseEntity<ApiResponse<PageResponse>> findAllSchedules(
            @RequestParam(defaultValue = "1", name = "page") final Integer page,
            @RequestParam(required = false, name = "keyword") final String keyword,
            @RequestParam(required = false, name = "sort") final String sort,
            @RequestParam(required = false, name = "area") final Long area
    ) {
        final Page<ScheduleResponse> schedules = scheduleService.findAllSchedules(page, keyword, sort, area);
        final PagingButtonInfo pagingButtonInfo = Pagination.getPagingButtonInfo(schedules);
        final PageResponse pagingResponse = PageResponse.of(schedules.getContent(), pagingButtonInfo);
        return ResponseEntity.ok(ApiResponse.success(pagingResponse));
    }

    // TODO 일정 상세 조회
    @GetMapping("/{scheduleId}")
    public ResponseEntity<ApiResponse<ScheduleDetailResponse>> findByScheduleId(@PathVariable(name = "scheduleId") final Long scheduleId) {
//        final ScheduleDetailResponse scheduleDetailResponse =
//                scheduleService.getFindByScheduleId(scheduleId);
        final ScheduleDetailResponse scheduleDetailResponse =
                scheduleService.findByScheduleId(scheduleId);
        return ResponseEntity.ok(ApiResponse.success(scheduleDetailResponse));
    }

    // TODO 일정 등록
    @PostMapping()
    public ResponseEntity<ApiResponse> saveSchedule(
            @AuthenticationPrincipal CustomUser loginUser,
            @RequestPart @Valid final ScheduleCreateRequest scheduleRequest,
            @RequestPart final MultipartFile image
    ) {
        Long userId = loginUser.getUserId();
        final Long scheduleId = scheduleService.save(scheduleRequest, userId);
        imageService.save(ImageKind.SCHEDULE, scheduleId, image);

        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.success(URI.create("/api/v1/schedules/" + scheduleId)));
    }

    // TODO 일정 수정
    @PutMapping("/{scheduleId}")
    public ResponseEntity<ApiResponse> updateSchedule(
            @AuthenticationPrincipal CustomUser loginUser,
            @PathVariable final Long scheduleId,
            @RequestPart @Valid final ScheduleUpdateRequest scheduleUpdateRequest
    ) {
        Long userId = loginUser.getUserId();
        scheduleService.updateSchedule(scheduleId, scheduleUpdateRequest, userId);

        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.success(URI.create("/api/v1/schedules/" + scheduleId)));
    }

    // TODO 일정 썸네일 수정
    @PutMapping("/{scheduleId}/thumbnail")
    public ResponseEntity<ApiResponse> updateScheduleThumbnail(
            @AuthenticationPrincipal CustomUser loginUser,
            @PathVariable final Long scheduleId,
            @RequestPart final MultipartFile image) {

        Long userId = loginUser.getUserId();
        scheduleService.updateThumbnail(userId, scheduleId, image);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(URI.create("/api/v1/schedules/" + scheduleId)));
    }

    // TODO 일정 삭제
    @DeleteMapping("/{scheduleId}")
    public ResponseEntity<ApiResponse> removeSchedule(
            @AuthenticationPrincipal CustomUser loginUser,
            @PathVariable final Long scheduleId) {
        Long userId = loginUser.getUserId();
        scheduleService.removeSchedule(scheduleId, userId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(ApiResponse.success("일정이 삭제 되었습니다."));
    }

    // TODO 일정 계획 등록
    @PostMapping("/{scheduleId}/item")
    public ResponseEntity<ApiResponse> saveItem(
            @RequestBody @Valid final ScheduleItemCreateRequest scheduleItemRequest,
            @PathVariable final Long scheduleId
    ) {

        scheduleService.saveItem(scheduleItemRequest, scheduleId);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(URI.create("/api/v1/schedules/")));
    }

    // TODO 일정 계획 수정
    @PutMapping("/{scheduleItemId}/item")
    public ResponseEntity<ApiResponse> updateItem(
            @AuthenticationPrincipal CustomUser loginUser,
            @RequestBody @Valid final ScheduleItemUpdateRequest scheduleItemUpdateRequest,
            @PathVariable final Long scheduleItemId
    ) {
        Long userId = loginUser.getUserId();
        scheduleService.updateItem(scheduleItemUpdateRequest, scheduleItemId, userId);


        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(URI.create("/api/v1/schedules/")));
    }

    // TODO 일정 공개 여부 변경
    @PutMapping("/{scheduleId}/status")
    public ResponseEntity<ApiResponse> changeStatus(
            @AuthenticationPrincipal CustomUser loginUser,
            @PathVariable final Long scheduleId,
            @RequestParam String status
    ) {
        Long userId = loginUser.getUserId();
        scheduleService.changeStatus(scheduleId, userId, status);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(URI.create("/api/v1/schedules/")));
    }

    // TODO 일정 계획 삭제
    @DeleteMapping("/{scheduleItemId}/item")
    public ResponseEntity<ApiResponse> removeItem(
            @AuthenticationPrincipal CustomUser loginUser,
            @PathVariable final Long scheduleItemId) {
        Long userId = loginUser.getUserId();
        scheduleService.removeItem(scheduleItemId, userId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(ApiResponse.success("일정 계획이 삭제 되었습니다."));

    }

    // TODO 일정 신청
    @PostMapping("/{scheduleId}/apply")
    public ResponseEntity<ApiResponse> applySchedule(@PathVariable final Long scheduleId,
                                                     @AuthenticationPrincipal CustomUser loginUser,
                                                     @RequestBody @Valid final ScheduleParticipantRequest scheduleParticipantRequest
    ) {
        Long userId = loginUser.getUserId();
        final Long scheduleParticipantId = scheduleParticipantService.save(scheduleParticipantRequest, scheduleId, userId);
        return ResponseEntity.ok(ApiResponse.success("일정 신청이 되었습니다."));

    }

    // TODO 일정 신청 승인
    @PutMapping("/{scheduleParticipantId}/accept")
    public ResponseEntity<ApiResponse> acceptSchedule(@PathVariable final Long scheduleParticipantId,
                                                      @RequestBody @Valid final ScheduleParticipantAcceptRequest scheduleParticipantAcceptRequest) {

        scheduleParticipantService.accept(scheduleParticipantAcceptRequest, scheduleParticipantId);
        return ResponseEntity.ok(ApiResponse.success("일정 신청이 승인되었습니다."));
    }

    // TODO 일정 신청 반려
    @PutMapping("/{scheduleParticipantId}/rejected")
    public ResponseEntity<ApiResponse> rejectedSchedule(@PathVariable final Long scheduleParticipantId,
                                                        @RequestBody @Valid final ScheduleParticipantRejectedRequest scheduleParticipantRejectedRequest) {

        scheduleParticipantService.reject(scheduleParticipantRejectedRequest, scheduleParticipantId);
        return ResponseEntity.ok(ApiResponse.success(("일정 신청이 거절되었습니다.")));
    }
}
