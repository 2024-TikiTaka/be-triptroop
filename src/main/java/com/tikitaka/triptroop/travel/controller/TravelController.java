package com.tikitaka.triptroop.travel.controller;

import com.tikitaka.triptroop.common.dto.response.ApiResponse;
import com.tikitaka.triptroop.common.page.PageResponse;
import com.tikitaka.triptroop.common.page.Pagination;
import com.tikitaka.triptroop.common.page.PagingButtonInfo;
import com.tikitaka.triptroop.image.domain.type.ImageKind;
import com.tikitaka.triptroop.image.service.ImageService;
import com.tikitaka.triptroop.travel.domain.entity.Travel;
import com.tikitaka.triptroop.travel.dto.request.TravelRequest;
import com.tikitaka.triptroop.travel.dto.response.TravelResponse;
import com.tikitaka.triptroop.travel.dto.response.TravelsResponse;
import com.tikitaka.triptroop.travel.service.TravelService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/travels")
@RequiredArgsConstructor
public class TravelController {

    private final TravelService travelService;

    private final ImageService imageService;

    /* 전체 게시글 조회 */
    @GetMapping()
    public ResponseEntity<ApiResponse<PageResponse>> findAll(
            @RequestParam(defaultValue = "1") final Integer page,
            @RequestParam(required = false) final Long areaId,
            @RequestParam(required = false) final Long categoryId,
            @RequestParam(required = false) final String title) {


        final Page<TravelsResponse> travels = travelService.findAll(page, areaId, categoryId, title);
        final PagingButtonInfo pagingButtonInfo = Pagination.getPagingButtonInfo(travels);
        final PageResponse pagingResponse = PageResponse.of(travels.getContent(), pagingButtonInfo);

        return ResponseEntity.ok(ApiResponse.success(pagingResponse));
    }

    /* 상세 게시글 조회 */
    @GetMapping("/{travelId}")
    public ResponseEntity<ApiResponse<TravelResponse>> findTravelId(
            @PathVariable final Long travelId) {


        final TravelResponse travelResponse = travelService.findByTravelId(travelId);

        return ResponseEntity.ok(ApiResponse.success(travelResponse));
    }


    /* 여행 소개 등록 */
    @PostMapping("/insert")
    public ResponseEntity<ApiResponse<Void>> save(
            @RequestPart @Valid final TravelRequest travelRequest,
            @RequestPart final MultipartFile image) {

        final Long travelId = travelService.save(travelRequest, 2L);
        imageService.save(ImageKind.TRAVEL, travelId, image);

        return ResponseEntity.created(URI.create("/api/v1/travels/" + travelId)).build();
    }


    @PostMapping(value = "/{travelId}/upload")
    public ResponseEntity<Void> imageSave(@RequestPart final MultipartFile image,
                                          @PathVariable final Long travelId) {

        imageService.save(ImageKind.TRAVEL, travelId, image);
        return ResponseEntity.created(URI.create("/api/v1/travels" + travelId)).build();
    }

    /* 상세조회 (Q*/
    @GetMapping("/{id}")
    public Optional<Travel> getTravelByIdAndVisibility(@PathVariable Long id, @RequestParam String visibility) {
        return travelService.getTravelByIdAndVisibility(id, visibility);
    }
}
