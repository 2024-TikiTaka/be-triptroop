package com.tikitaka.triptroop.travel.controller;

import com.tikitaka.triptroop.common.dto.response.CommonResponse;
import com.tikitaka.triptroop.common.paging.Pagination;
import com.tikitaka.triptroop.common.paging.PagingButtonInfo;
import com.tikitaka.triptroop.common.paging.PagingResponse;
import com.tikitaka.triptroop.image.domain.type.ImageKind;
import com.tikitaka.triptroop.image.service.ImageService;
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
@RequestMapping("/api/v1/travels")
@RequiredArgsConstructor
public class TravelController {

    private final TravelService travelService;

    private final ImageService imageService;

    /* 전체 게시글 조회 */
    @GetMapping()
    public ResponseEntity<CommonResponse<PagingResponse>> findAll(
            @RequestParam(defaultValue = "1") final Integer page,
            @RequestParam(required = false) final Long areaId,
            @RequestParam(required = false) final Long categoryId) {

        final Page<TravelResponse> travels = travelService.findAll(page, areaId, categoryId);
        final PagingButtonInfo pagingButtonInfo = Pagination.getPagingButtonInfo(travels);
        final PagingResponse pagingResponse = PagingResponse.of(travels.getContent(), pagingButtonInfo);

        return ResponseEntity.ok(CommonResponse.success(pagingResponse));
    }

    /* 여행 소개 등록 */
    @PostMapping("/{travelId}/insert")
    public ResponseEntity<CommonResponse<Void>> save(
            @RequestBody @Valid final TravelRequest travelRequest

    ) {

        final Long travelId = travelService.save(travelRequest, 2L);
        return ResponseEntity.created(URI.create("/api/v1/travels" + travelId)).build();
    }

    @PostMapping(value = "/{travelId}/upload")
    public ResponseEntity<Void> imageSave(@RequestPart final MultipartFile image,
                                          @PathVariable final Long travelId) {

        imageService.save(ImageKind.TRAVEL, travelId, image);
        return ResponseEntity.created(URI.create("/api/v1/travels" + travelId)).build();
    }
}
