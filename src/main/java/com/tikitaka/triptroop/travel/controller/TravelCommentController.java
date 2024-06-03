package com.tikitaka.triptroop.travel.controller;


import com.tikitaka.triptroop.travel.service.TravelCommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/travels")
@RequiredArgsConstructor
public class TravelCommentController {

    private final TravelCommentService commentService;

    /* 댓글 등록 */
//    @PostMapping("/{travelId}/comment")
//    public ResponseEntity<ApiResponse<Void>> addComment(
//            @RequestBody @Valid final TravelCommentRequest commentRequest) {
//
//        final Long commentId = commentService.save(commentRequest);
//
//        return ResponseEntity.created(URI.create("/api/v1/travels/comment/" + commentId)).build();
//    }

}
