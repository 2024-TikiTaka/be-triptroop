package com.tikitaka.triptroop.admin.controller;


import com.tikitaka.triptroop.common.dto.response.ApiResponse;
import com.tikitaka.triptroop.inquiry.domain.entity.Inquiry;
import com.tikitaka.triptroop.inquiry.dto.request.InquiryReplyRequest;
import com.tikitaka.triptroop.inquiry.dto.response.InquiryDetailResponse;
import com.tikitaka.triptroop.inquiry.dto.response.InquiryListResponse;
import com.tikitaka.triptroop.inquiry.service.InquiryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/admin/inquiry")
public class AdminInquiryController {

    private final InquiryService inquiryService;

    /* 1. 문의 관리 > 문의 목록 조회 */
    @GetMapping("")
    public ResponseEntity<ApiResponse> getInquriryList() {
        final List<InquiryListResponse> inquryList = inquiryService.getInquriryList();
        return ResponseEntity.ok(ApiResponse.success("문의 목록 조회에 성공하였습니다.", inquryList));
    }

    /* 2. 문의 관리 > 문의 상세 조회 */
    @GetMapping("/{inquiryId}")
    public ResponseEntity<ApiResponse> getInquiryDetail(@PathVariable final Long inquiryId) {
        final InquiryDetailResponse inquiryDetail = inquiryService.getInquiryDetail(inquiryId);
        return ResponseEntity.ok(ApiResponse.success("문의 상세 조회에 성공하였습니다.", inquiryDetail));
    }

    /* 3. 문의 관리 > 답변 등록 */
    @PatchMapping("/{inquiryId}/reply")
    public ResponseEntity<ApiResponse> saveInquiryReply(
            @PathVariable final Long inquiryId,
            @RequestPart @Valid final InquiryReplyRequest inquiryReplyRequest,
            @RequestPart final List<MultipartFile> images
    ) {
        final Inquiry updateInquiry = inquiryService.inquiryReplySave(inquiryId, inquiryReplyRequest, images);
        return ResponseEntity.ok(ApiResponse.success("답변 등록에 성공하였습니다.", updateInquiry));
    }
}
