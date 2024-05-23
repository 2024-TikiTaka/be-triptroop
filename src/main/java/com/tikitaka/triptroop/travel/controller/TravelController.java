package com.tikitaka.triptroop.travel.controller;

import com.tikitaka.triptroop.travel.dto.response.TravelResponse;
import com.tikitaka.triptroop.travel.service.TravelService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/travels") // <- "" 안에 매핑할 주소를 적어주세요. ( ex) /schedule/post )
@RequiredArgsConstructor
public class TravelController {

    /* 내용을 작성해주세요. */
    private final TravelService travelService;

    /* 전체 게시글 조회 */
    @GetMapping()
    public ResponseEntity<TravelResponse> getTravels(
            @RequestMapping(defaultValue = "1") final Integer page
    ) {

        final Page<TravelResponse> travels = travelService.findAll(page);


        return ResponseEntity.ok(travels);
    }

}
