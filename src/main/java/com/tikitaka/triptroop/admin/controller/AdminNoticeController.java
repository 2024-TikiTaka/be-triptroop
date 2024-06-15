package com.tikitaka.triptroop.admin.controller;


import com.tikitaka.triptroop.admin.service.AdminNoticeService;
import com.tikitaka.triptroop.common.dto.response.ApiResponse;
import com.tikitaka.triptroop.notice.domain.entity.Notice;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/admin/notice")
public class AdminNoticeController {

    private final AdminNoticeService adminNoticeService;

    /* 1. 공지 관리 > 공지 목록 조회 */
    @GetMapping("")
    public ResponseEntity<ApiResponse> getNoticeList() {
        final List<Notice> noticeList = adminNoticeService.getNoticeList();
        return ResponseEntity.ok(ApiResponse.success("공지 목록 조회에 성공하였습니다.", noticeList));
    }

    /* 2. 지 관리 > 공지 상세 조회 */
//    @GetMapping("/{noticeId}")
//    public ResponseEntity<ApiResponse> getNoticeDetail(@PathVariable final Long noticeId) {
//        final AdminInquiryDetailResponse inquiryDetail = inquiryService.getInquiryDetail(inquiryId);
//        return ResponseEntity.ok(ApiResponse.success("문의 상세 조회에 성공하였습니다.", inquiryDetail));
//    }
//
//    /* 3. 문의 관리 > 답변 등록 */
//    @PatchMapping("/{inquiryId}/reply")
//    public ResponseEntity<ApiResponse> saveInquiryReply(
//            @PathVariable final Long inquiryId,
//            @RequestPart @Valid final AdminInquiryReplyRequest adminInquiryReplyRequest,
//            @RequestPart final List<MultipartFile> images
//    ) {
//        final Inquiry updateInquiry = inquiryService.inquiryReplySave(inquiryId, adminInquiryReplyRequest, images);
//        return ResponseEntity.ok(ApiResponse.success("답변 등록에 성공하였습니다.", updateInquiry));
//    }
}
