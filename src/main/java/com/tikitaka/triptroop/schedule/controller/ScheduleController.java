package com.tikitaka.triptroop.schedule.controller;

import com.tikitaka.triptroop.common.page.PageResponse;
import com.tikitaka.triptroop.common.page.Pagination;
import com.tikitaka.triptroop.common.page.PagingButtonInfo;
import com.tikitaka.triptroop.image.domain.type.ImageKind;
import com.tikitaka.triptroop.image.service.ImageService;
import com.tikitaka.triptroop.schedule.dto.request.ScheduleCreateRequest;
import com.tikitaka.triptroop.schedule.dto.request.ScheduleItemCreateRequest;
import com.tikitaka.triptroop.schedule.dto.response.ScheduleResponse;
import com.tikitaka.triptroop.schedule.service.ScheduleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.net.URI;

@RestController
@RequestMapping("/api/v1/schedules")
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    private final ImageService imageService;

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

    @GetMapping("/{id}")
    public ResponseEntity<ScheduleResponse> findByScheduleId(@PathVariable(name = "id") final Long scheduleId) {
        final ScheduleResponse scheduleDetailResponse = scheduleService.getFindByScheduleId(scheduleId);
        return ResponseEntity.ok(scheduleDetailResponse);
    }

    @PostMapping()
    public ResponseEntity<Void> save(@RequestBody @Valid final ScheduleCreateRequest scheduleRequest
            /* @AuthenticationPrincipalfinal */) {
        final Long scheduleId = scheduleService.save(scheduleRequest, 2L); // TODO: userId 받기
        return ResponseEntity.created(URI.create("/api/v1/schedule/" + scheduleId)).build();
    }

    @PostMapping("/{scheduleId}/item")
    public ResponseEntity<Void> saveItem(@RequestBody @Valid final ScheduleItemCreateRequest scheduleItemRequest,
                                         @PathVariable final Long scheduleId) {

        scheduleService.saveItem(scheduleItemRequest, scheduleId);
        return ResponseEntity.created(URI.create("/api/v1/schedule/" + scheduleId)).build();
    }

    @PostMapping("/{imageId}/upload")
    public ResponseEntity<Void> uploadImage(@RequestPart final MultipartFile image,
                                            @PathVariable final Long imageId) {

        imageService.save(ImageKind.SCHEDULE, imageId, image);
        return ResponseEntity.created(URI.create("/api/v1/schedule/" + imageId)).build();
    }
}
