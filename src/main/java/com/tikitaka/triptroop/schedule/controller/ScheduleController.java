package com.tikitaka.triptroop.schedule.controller;

import com.tikitaka.triptroop.common.dto.response.ApiResponse;
import com.tikitaka.triptroop.common.page.PageResponse;
import com.tikitaka.triptroop.common.page.Pagination;
import com.tikitaka.triptroop.common.page.PagingButtonInfo;
import com.tikitaka.triptroop.image.domain.type.ImageKind;
import com.tikitaka.triptroop.image.service.ImageService;
import com.tikitaka.triptroop.place.service.PlaceService;
import com.tikitaka.triptroop.schedule.dto.request.*;
import com.tikitaka.triptroop.schedule.dto.response.ScheduleDetailResponse;
import com.tikitaka.triptroop.schedule.dto.response.ScheduleParticipantProfileResponse;
import com.tikitaka.triptroop.schedule.dto.response.ScheduleResponse;
import com.tikitaka.triptroop.schedule.service.ScheduleParticipantService;
import com.tikitaka.triptroop.schedule.service.ScheduleService;
import com.tikitaka.triptroop.user.domain.type.CustomUser;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.net.URI;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/schedules")
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    private final PlaceService placeService;

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
    @PostMapping("/regist")
    public ResponseEntity<ApiResponse> saveSchedule(
            @AuthenticationPrincipal CustomUser loginUser,
            @RequestPart final ScheduleCreateRequest scheduleRequest,
            @RequestPart(required = false) final List<ScheduleItemCreateRequest> scheduleItemRequest,
            @RequestPart final MultipartFile image
    ) {
        Long userId = loginUser.getUserId();
        final Long scheduleId = scheduleService.save(scheduleRequest, userId);

        // 이미지 저장
        if (image != null) {
            imageService.save(ImageKind.SCHEDULE, scheduleId, image);
        } else {
            // 이미지가 없는 경우 처리 로직 추가
        }

        // 일정 항목 저장 및 처리
        if (scheduleItemRequest != null && !scheduleItemRequest.isEmpty()) {
            Long placeId = placeService.saveSchedulePlace(scheduleItemRequest);
            scheduleService.saveItem(scheduleItemRequest, scheduleId, placeId);
        }
        scheduleParticipantService.saveUser(userId, scheduleId);

        return ResponseEntity.status(HttpStatus.CREATED)
                             .body(ApiResponse.success(URI.create("/api/v1/schedules/" + scheduleId)));
    }


    // TODO 일정 수정
    @PutMapping("/{scheduleId}/modify")
    public ResponseEntity<ApiResponse> updateSchedule(
            @AuthenticationPrincipal CustomUser loginUser,
            @PathVariable final Long scheduleId,
            @RequestPart(required = false) @Valid ScheduleUpdateRequest scheduleUpdateRequest,
            @RequestPart(required = false) final MultipartFile image
    ) {
        log.info("@!#@!#@!#@!#@!#", image);
        String status = scheduleUpdateRequest.getVisibility();
        Long userId = loginUser.getUserId();
        scheduleService.updateSchedule(scheduleId, scheduleUpdateRequest, userId);
        scheduleService.changeStatus(scheduleId, userId, status);
        // 이미지 저장
        if (image != null) {
            imageService.updateImage(ImageKind.SCHEDULE, scheduleId, image);
        } else {
            // 이미지가 없는 경우 처리 로직 추가
        }

        return ResponseEntity.status(HttpStatus.CREATED)
                             .body(ApiResponse.success(URI.create("/api/v1/schedules/" + scheduleId)));
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
    @DeleteMapping("/{scheduleId}/remove")
    public ResponseEntity<ApiResponse> removeSchedule(
            @AuthenticationPrincipal CustomUser loginUser,
            @PathVariable(name = "scheduleId") final Long scheduleId) {
        Long userId = loginUser.getUserId();
        scheduleService.removeSchedule(scheduleId, userId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(ApiResponse.success("일정이 삭제 되었습니다."));
    }

    // TODO 일정 계획 등록
    //    @PostMapping("/regist/item")
    //    public ResponseEntity<ApiResponse> saveItem(
    //            @AuthenticationPrincipal CustomUser loginUser,
    //            @RequestBody @Valid final ScheduleItemCreateRequest scheduleItemRequest,
    //            @PathVariable final Long scheduleId
    //    ) {
    //
    //        Long placeId = placeService.savePlace(scheduleItemRequest, loginUser.getUserId());
    //
    //        scheduleService.saveItem(scheduleItemRequest, scheduleId, placeId);
    //
    //        return ResponseEntity.status(HttpStatus.CREATED)
    //                .body(ApiResponse.success(URI.create("/api/v1/schedules/")));
    //    }

    // TODO 일정 계획 수정
    @PutMapping("/{scheduleItemId}/item")
    public ResponseEntity<ApiResponse> updateItem(
            @AuthenticationPrincipal CustomUser loginUser,
            @RequestBody(required = false) @Valid final ScheduleItemUpdateRequest scheduleItemUpdateRequest,
            @PathVariable(name = "scheduleItemId") final Long scheduleItemId
    ) {

        Long placeId = scheduleService.updateItem(scheduleItemUpdateRequest, scheduleItemId);
        placeService.updatePlace(scheduleItemUpdateRequest, placeId);


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
    @DeleteMapping("/{scheduleItemId}/remove-item")
    public ResponseEntity<ApiResponse> removeItem(
            @AuthenticationPrincipal CustomUser loginUser,
            @PathVariable final Long scheduleItemId) {
        Long userId = loginUser.getUserId();
        scheduleService.removeItem(scheduleItemId, userId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(ApiResponse.success("일정 계획이 삭제 되었습니다."));

    }

    // TODO 일정 신청자 리스트 조회
    @GetMapping("{scheduleId}/schedulesParticipantList")
    public ResponseEntity<ApiResponse<List<ScheduleParticipantProfileResponse>>> findAllSchedulesParticipant(
            @PathVariable final Long scheduleId
    ) {
        final List<ScheduleParticipantProfileResponse> scheduleParticipantProfile = scheduleParticipantService.findAllSchedulesParticipants(scheduleId);
        return ResponseEntity.ok(ApiResponse.success(scheduleParticipantProfile));
    }

    // TODO 일정 신청
    @PostMapping("/{scheduleId}/apply")
    public ResponseEntity<ApiResponse> applySchedule(@PathVariable final Long scheduleId,
                                                     @AuthenticationPrincipal CustomUser loginUser
            //                                                     @RequestBody @Valid final ScheduleParticipantRequest scheduleParticipantRequest
    ) {
        Long userId = loginUser.getUserId();
        scheduleParticipantService.save(scheduleId, userId);
        return ResponseEntity.ok(ApiResponse.success("일정 신청이 되었습니다."));

    }

    // TODO 일정 신청 승인
    @PutMapping("/{scheduleParticipantId}/accept")
    public ResponseEntity<ApiResponse> acceptSchedule(@PathVariable final Long scheduleParticipantId
    ) {
        scheduleParticipantService.accept(scheduleParticipantId);
        return ResponseEntity.ok(ApiResponse.success("일정 신청이 승인되었습니다."));
    }

    // TODO 일정 신청 반려
    @PutMapping("/{scheduleParticipantId}/rejected")
    public ResponseEntity<ApiResponse> rejectedSchedule(@PathVariable final Long scheduleParticipantId,
                                                        @RequestBody @Valid final ScheduleParticipantRejectedRequest scheduleParticipantRejectedRequest) {

        scheduleParticipantService.reject(scheduleParticipantRejectedRequest, scheduleParticipantId);
        return ResponseEntity.ok(ApiResponse.success(("일정 신청이 거절되었습니다.")));
    }

    // TODO 일정 리뷰 등록
    @PostMapping("/{scheduleId}/review")
    public ResponseEntity<ApiResponse> writeReview(
            @AuthenticationPrincipal CustomUser loginUser,
            @PathVariable final Long scheduleId,
            @RequestBody @Valid final ScheduleReviewRequest scheduleReviewRequest
    ) {
        Long userId = loginUser.getUserId();
        scheduleParticipantService.writeReview(scheduleId, userId, scheduleReviewRequest);
        return ResponseEntity.ok(ApiResponse.success(("일정 리뷰 작성이 완료되었습니다.")));

    }

    // TODO 일정 리뷰 수정
    @PutMapping("/{scheduleId}/review")
    public ResponseEntity<ApiResponse> updateReview(
            @AuthenticationPrincipal CustomUser loginUser,
            @PathVariable final Long scheduleId,
            @RequestBody @Valid final ScheduleReviewRequest scheduleReviewRequest
    ) {
        Long userId = loginUser.getUserId();
        scheduleParticipantService.updateReview(scheduleId, userId, scheduleReviewRequest);
        return ResponseEntity.ok(ApiResponse.success(("일정 리뷰가 수정되었습니다.")));

    }

    // TODO 일정 리뷰 삭제
    @DeleteMapping("/{scheduleId}/review")
    public ResponseEntity<ApiResponse> removeReview(
            @AuthenticationPrincipal CustomUser loginUser,
            @PathVariable final Long scheduleId
    ) {
        Long userId = loginUser.getUserId();
        scheduleParticipantService.removeReview(scheduleId, userId);
        return ResponseEntity.ok(ApiResponse.success(("일정 리뷰가 삭제되었습니다.")));

    }
}
