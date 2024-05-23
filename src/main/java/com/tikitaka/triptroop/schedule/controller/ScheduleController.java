package com.tikitaka.triptroop.schedule.controller;

import com.tikitaka.triptroop.image.service.ImageService;
import com.tikitaka.triptroop.schedule.domain.type.ImageKind;
import com.tikitaka.triptroop.schedule.dto.request.ScheduleCreateRequest;
import com.tikitaka.triptroop.schedule.service.ScheduleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.net.URI;

@RestController
@RequestMapping("/api/v1/schedule") // <- "" 안에 매핑할 주소를 적어주세요. ( ex) /schedule/post )
@RequiredArgsConstructor
public class ScheduleController {
    private final ScheduleService scheduleService;
    private final ImageService imageService;

    /* 내용을 작성해주세요. */
    @PostMapping("/")
    public ResponseEntity<Void> save(
            @RequestBody @Valid final ScheduleCreateRequest scheduleRequest
//            @AuthenticationPrincipal final
    ) {
        final Long scheduleId = scheduleService.save(scheduleRequest, 2L);// userId 받기
        return ResponseEntity.created(URI.create("/api/v1/schedule/" + scheduleId)).build();
    }

    @PostMapping("/{scheduleId}/upload")
    public ResponseEntity<Void> imageSave(
            @RequestPart final MultipartFile image,
            @PathVariable final Long scheduleId
    ) {

        imageService.save(ImageKind.SCHEDULE, scheduleId, image);
        return ResponseEntity.created(URI.create("/api/v1/schedule/" + scheduleId)).build();
    }

}
