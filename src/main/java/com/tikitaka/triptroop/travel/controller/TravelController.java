package com.tikitaka.triptroop.travel.controller;

import com.tikitaka.triptroop.common.dto.response.CommonResponse;
import com.tikitaka.triptroop.common.paging.Pagination;
import com.tikitaka.triptroop.common.paging.PagingButtonInfo;
import com.tikitaka.triptroop.common.paging.PagingResponse;
import com.tikitaka.triptroop.travel.dto.request.TravelRequest;
import com.tikitaka.triptroop.travel.dto.response.TravelResponse;
import com.tikitaka.triptroop.travel.service.TravelService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.net.URI;

@RestController
@RequestMapping("/api/v1/travels") // <- "" 안에 매핑할 주소를 적어주세요. ( ex) /schedule/post )
@RequiredArgsConstructor
public class TravelController {

    /* 내용을 작성해주세요. */
    private final TravelService travelService;

    /* 전체 게시글 조회 */
    @GetMapping()
    public ResponseEntity<CommonResponse<PagingResponse>> findByAll(
            @RequestParam(defaultValue = "1") final Integer page,
            @RequestParam(required = false) final Long areaId,
            @RequestParam(required = false) final Long categoryId
    ) {

        final Page<TravelResponse> travels = travelService.findAll(page, areaId, categoryId);
        final PagingButtonInfo pagingButtonInfo = Pagination.getPagingButtonInfo(travels);
        final PagingResponse pagingResponse = PagingResponse.of(travels.getContent(), pagingButtonInfo);

        return ResponseEntity.ok(CommonResponse.success(pagingResponse));
    }

    /* 여행 소개 등록 */
    @PostMapping("/insert")
    public ResponseEntity<CommonResponse<Void>> save(
            @RequestPart @Valid final TravelRequest travelRequest,
            @RequestPart final MultipartFile travelImg
    ) {

        final Long travelId = travelService.save(travelRequest, travelImg, 2L);

        return ResponseEntity.created(URI.create("/api/v1/travels/insert" + travelId)).build();
    }


}
