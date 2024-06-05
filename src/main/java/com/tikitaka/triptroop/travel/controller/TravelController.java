package com.tikitaka.triptroop.travel.controller;

import com.tikitaka.triptroop.common.dto.response.ApiResponse;
import com.tikitaka.triptroop.common.page.PageResponse;
import com.tikitaka.triptroop.common.page.Pagination;
import com.tikitaka.triptroop.common.page.PagingButtonInfo;
import com.tikitaka.triptroop.image.domain.type.ImageKind;
import com.tikitaka.triptroop.image.service.ImageService;
import com.tikitaka.triptroop.travel.dto.request.TravelRequest;
import com.tikitaka.triptroop.travel.dto.request.TravelUpdateRequest;
import com.tikitaka.triptroop.travel.dto.response.TravelInfoResponse;
import com.tikitaka.triptroop.travel.dto.response.TravelsResponse;
import com.tikitaka.triptroop.travel.service.TravelService;
import com.tikitaka.triptroop.user.domain.type.CustomUser;
import jakarta.persistence.EntityManager;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.net.URI;
import java.util.List;

/**
 * @AuthenticationPrincipal CustomUser loginUser
 * => 현재 로그인한 회원의 정보를 받아와서 아이디 넘겨주기(아이디는 PK 값 말하는 것)
 * => loginUser.getUserId()
 */
@RestController
@RequestMapping("/api/v1/travels")
@RequiredArgsConstructor
public class TravelController {

    private final TravelService travelService;

    private final ImageService imageService;

    private final EntityManager entityManager;

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


    /* 여행 소개 등록 */
    @PostMapping()
    public ResponseEntity<ApiResponse> save(
            @AuthenticationPrincipal CustomUser loginUser,
            @RequestPart @Valid final TravelRequest travelRequest,
            @RequestPart final List<MultipartFile> images) {

        final Long travelId = travelService.save(travelRequest, loginUser.getUserId(), images);


        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(URI.create("/api/v1/travels/" + travelId)));
    }


//    @PostMapping(value = "/{travelId}/upload")
//    public ResponseEntity<Void> saveImage(@RequestPart final MultipartFile image,
//                                          @PathVariable final Long travelId) {
//
//        imageService.save(ImageKind.TRAVEL, travelId, image);
//        return ResponseEntity.created(URI.create("/api/v1/travels" + travelId)).build();
//    }

    /* 게시글 상세 조회 (된거)*/
//    @GetMapping("/travel/{travelId}")
//    public ResponseEntity<TravelCommentUserResponse> getTravelCommentUser(
//            @PathVariable final Long travelId
//    ) {
//        TravelCommentUserResponse travelCommentUserResponse = travelService.getTravelCommentUser(travelId);
//        return ResponseEntity.ok(travelCommentUserResponse);
//    }

    /* 게시글 상세 조회 (수정본) */
//    @GetMapping("/{travelId}")
//    public ResponseEntity<TravelDetailResponse> findTravelDetail(
//            @PathVariable final Long travelId
//    ) {
//        TravelDetailResponse travelDetailResponse = travelService.findTravelDetail(travelId);
//        return ResponseEntity.ok(travelDetailResponse);
//    }

    /* 게시글 상세 조회 (최종 수정본) */
    @GetMapping("/{travelId}")
    public ResponseEntity<ApiResponse> findTravel(
            @PathVariable final Long travelId

    ) {
        TravelInfoResponse travelInfo = travelService.getTravelInfo(travelId);
        return ResponseEntity.ok(ApiResponse.success(travelInfo));
    }

    /* 게시글 상세 조회 (하는중)*/
//    @GetMapping("/{travelId}")
//    public ResponseEntity<Optional<TravelResponse>> findTravel(
//            @PathVariable final Long travelId) {
//
//        Optional<TravelResponse> travelResponse = travelService.findTravel(travelId, Visibility.PUBLIC);
//
//        return ResponseEntity.ok(travelResponse);
//    }


    /* 게시글 수정 */
    @PutMapping("/{travelId}")
    public ResponseEntity<ApiResponse> updateTravel(
            @AuthenticationPrincipal CustomUser loginUser,
            @PathVariable final Long travelId,
            @RequestPart @Valid final TravelUpdateRequest travelRequest,
            @RequestPart(required = false) final MultipartFile image) {

        travelService.updateTravel(travelId, travelRequest, loginUser.getUserId());
        imageService.updateImage(ImageKind.TRAVEL, travelId, image);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(URI.create("/api/v1/travels/" + travelId)));
    }

    /* 게시글을 삭제해주세요~ ♬ */
    @DeleteMapping("/{travelId}")
    public ResponseEntity<ApiResponse> deleteTravel(
            @AuthenticationPrincipal CustomUser loginUser,
            @PathVariable final Long travelId) {

        travelService.deleteTravel(travelId, loginUser.getUserId());
        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                .body(ApiResponse.success("삭제가 완료되었습니다."));

    }

    /* 여행지 소개 공개 / 비공개 */
    @PutMapping("/{travelId}/")
    public ResponseEntity<ApiResponse> updateStatus(
            @AuthenticationPrincipal CustomUser loginUser,
            @PathVariable final Long travelId,
            @RequestParam String status
    ) {

        travelService.updateStatus(loginUser.getUserId(), travelId, status);

        return ResponseEntity.ok(ApiResponse.success("공개 상태가 변경되었습니다."));


    }

}
