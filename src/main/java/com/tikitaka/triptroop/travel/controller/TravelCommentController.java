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
    private final TravelCommentService travelCommentService;

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
            @PathVariable final Long travelId

    ) {
        final Page<TravelCommentResponse> travelComment = commentService.findAll(page, travelId);
        final PagingButtonInfo pagingButtonInfo = Pagination.getPagingButtonInfo(travelComment);
        final PageResponse pageResponse = PageResponse.of(travelComment.getContent(), pagingButtonInfo);

        return ResponseEntity.ok(ApiResponse.success(pageResponse));

    }

    /* 댓글 수정 */
    @PutMapping("/comments/{commentId}")
    public ResponseEntity<Void> updateComment(
//            @AuthenticationPrincipal CustomUser loginUser,
            @PathVariable final Long commentId,
            @RequestBody @Valid final TravelCommentRequest commentRequest
    ) {
        travelCommentService.updateComment(/*loginUser.getUserId()*/ commentId, commentRequest);

        return ResponseEntity.created(URI.create("/api/v1/travels/comment/" + commentId)).build();

    }

    /* 댓글 삭제 */
    @DeleteMapping("/comments/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable final Long commentId) {

        travelCommentService.deleteTravelComment(commentId);

        return ResponseEntity.noContent().build();
    }


}
