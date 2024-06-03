package com.tikitaka.triptroop.travel.controller;


import com.tikitaka.triptroop.common.dto.response.ApiResponse;
import com.tikitaka.triptroop.common.page.PageResponse;
import com.tikitaka.triptroop.common.page.Pagination;
import com.tikitaka.triptroop.common.page.PagingButtonInfo;
import com.tikitaka.triptroop.travel.dto.request.TravelCommentRequest;
import com.tikitaka.triptroop.travel.dto.response.TravelCommentResponse;
import com.tikitaka.triptroop.travel.service.TravelCommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/v1/travels")
@RequiredArgsConstructor
public class TravelCommentController {

    private final TravelCommentService commentService;

    /* 댓글 등록 */
    @PostMapping("/{travelId}/comment")
    public ResponseEntity<ApiResponse<Void>> addComment(
            @RequestBody @Valid final TravelCommentRequest commentRequest) {

        final Long commentId = commentService.save(commentRequest);

        return ResponseEntity.created(URI.create("/api/v1/travels/comment/" + commentId)).build();
    }

    /* 댓글 조회 */
    @GetMapping("/{travelId}/comment")
    public ResponseEntity<ApiResponse<PageResponse>> findComments(
            @RequestParam(defaultValue = "1") final Integer page,
            @RequestParam(required = false) final Long travelId

    ) {
        final Page<TravelCommentResponse> travelComment = commentService.findAll(page, travelId);
        final PagingButtonInfo pagingButtonInfo = Pagination.getPagingButtonInfo(travelComment);
        final PageResponse pageResponse = PageResponse.of(travelComment.getContent(), pagingButtonInfo);

        return ResponseEntity.ok(ApiResponse.success(pageResponse));


    }

}
