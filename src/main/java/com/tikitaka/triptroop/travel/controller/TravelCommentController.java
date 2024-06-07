package com.tikitaka.triptroop.travel.controller;


import com.tikitaka.triptroop.common.dto.response.ApiResponse;
import com.tikitaka.triptroop.common.page.PageResponse;
import com.tikitaka.triptroop.common.page.Pagination;
import com.tikitaka.triptroop.common.page.PagingButtonInfo;
import com.tikitaka.triptroop.travel.dto.request.TravelCommentRequest;
import com.tikitaka.triptroop.travel.dto.response.TravelCommentResponse;
import com.tikitaka.triptroop.travel.service.TravelCommentService;
import com.tikitaka.triptroop.user.domain.type.CustomUser;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class TravelCommentController {

    private final TravelCommentService commentService;
    private final TravelCommentService travelCommentService;

    /**
     * 댓글 등록
     */
    @PostMapping("/travels/{travelId}/comments")
    public ResponseEntity<ApiResponse> addComment(
            @AuthenticationPrincipal CustomUser loginUser,
            @PathVariable Long travelId,
            @RequestBody @Valid @NotBlank String content) {

        commentService.save(travelId, loginUser.getUserId(), content);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("댓글 등록이 완료되었습니다."));
    }

    /* 댓글 조회 */
    @GetMapping("/travels/{travelId}/comments")
    public ResponseEntity<ApiResponse<PageResponse>> findComments(
            @RequestParam(defaultValue = "1") final Integer page,
            @PathVariable final Long travelId) {

        final Page<TravelCommentResponse> travelComment = commentService.findAll(page, travelId);
        final PagingButtonInfo pagingButtonInfo = Pagination.getPagingButtonInfo(travelComment);
        final PageResponse pageResponse = PageResponse.of(travelComment.getContent(), pagingButtonInfo);

        return ResponseEntity.ok(ApiResponse.success(pageResponse));
    }

    /* 댓글 수정 */
    @PutMapping("/comments/{commentId}")
    public ResponseEntity<ApiResponse> updateComment(
            @AuthenticationPrincipal CustomUser loginUser,
            @PathVariable final Long commentId,
            @RequestBody @Valid final TravelCommentRequest commentRequest) {

        travelCommentService.updateComment(loginUser.getUserId(), commentId, commentRequest);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("댓글 수정이 완료되었습니다."));

    }

    /* 댓글 삭제 */
    @DeleteMapping("/comments/{commentId}")
    public ResponseEntity<ApiResponse> deleteComment(
            @AuthenticationPrincipal CustomUser loginUser,
            @PathVariable final Long commentId) {

        travelCommentService.deleteTravelComment(loginUser.getUserId(), commentId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                .body(ApiResponse.success("댓글 삭제가 완료되었습니다."));
    }
}
