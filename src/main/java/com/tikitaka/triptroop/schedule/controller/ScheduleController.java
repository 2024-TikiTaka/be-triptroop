package com.tikitaka.triptroop.schedule.controller;

import com.tikitaka.triptroop.schedule.dto.request.ScheduleCreateRequest;
import com.tikitaka.triptroop.schedule.service.ScheduleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/api/v1/schedule") // <- "" 안에 매핑할 주소를 적어주세요. ( ex) /schedule/post )
@RequiredArgsConstructor
public class ScheduleController {
    private final ScheduleService scheduleService;

    /* 내용을 작성해주세요. */
    @PostMapping("/")
    public ResponseEntity<Void> save(
            @RequestBody @Valid final ScheduleCreateRequest scheduleRequest
    ) {
        final Long scheduleId = scheduleService.save(scheduleRequest);
        return ResponseEntity.created(URI.create("/api/v1/schedule/" + scheduleId)).build();
    }
}
