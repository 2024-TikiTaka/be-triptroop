package com.tikitaka.triptroop.admin.controller;


import com.tikitaka.triptroop.admin.dto.request.AdminInquiryReplyRequest;
import com.tikitaka.triptroop.admin.dto.response.AdminInquiryDetailResponse;
import com.tikitaka.triptroop.admin.dto.response.AdminInquiryListResponse;
import com.tikitaka.triptroop.admin.service.AdminInquiryService;
import com.tikitaka.triptroop.common.dto.response.ApiResponse;
import com.tikitaka.triptroop.common.page.PageResponse;
import com.tikitaka.triptroop.common.page.Pagination;
import com.tikitaka.triptroop.common.page.PagingButtonInfo;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/admin/inquiry")
public class AdminInquiryController {

    private final AdminInquiryService AdminInquiryService;

    /* 1. 문의 관리 > 문의 목록 조회 */
    @GetMapping("")
    public ResponseEntity<ApiResponse<PageResponse>> getInquiryList(
            @RequestParam(defaultValue = "1", name = "page") final Integer page,
            @RequestParam(defaultValue = "", name = "type") final String type,
            @RequestParam(defaultValue = "", name = "keyword") final String keyword,
            @RequestParam(defaultValue = "id", name = "sort") final String sort
    ) {
        final Page<AdminInquiryListResponse> inquryList = AdminInquiryService.getInquiryList(page, type, keyword, sort);
        final PagingButtonInfo pagingButtonInfo = Pagination.getPagingButtonInfo(inquryList);
        final PageResponse pagingResponse = PageResponse.of(inquryList.getContent(), pagingButtonInfo);

        return ResponseEntity.ok(ApiResponse.success("문의 목록 조회에 성공하였습니다.", pagingResponse));
    }

    /* 2. 문의 관리 > 문의 상세 조회 */
    @GetMapping("/{inquiryId}")
    public ResponseEntity<ApiResponse> getInquiryDetail(@PathVariable final Long inquiryId) {
        final AdminInquiryDetailResponse inquiryDetail = AdminInquiryService.getInquiryDetail(inquiryId);
        return ResponseEntity.ok(ApiResponse.success("문의 상세 조회에 성공하였습니다.", inquiryDetail));
    }

    /* 3. 문의 관리 > 답변 등록 */
    @PatchMapping("/{inquiryId}/reply")
    public ResponseEntity<ApiResponse> saveInquiryReply(
            @PathVariable final Long inquiryId,
            @RequestPart @Valid final AdminInquiryReplyRequest adminInquiryReplyRequest,
            @RequestPart(required = false) final List<MultipartFile> images
    ) {
        final AdminInquiryReplyRequest inquiryReply = AdminInquiryService.inquiryReplySave(inquiryId, adminInquiryReplyRequest, images);
        return ResponseEntity.ok(ApiResponse.success("답변 등록에 성공하였습니다."));
    }
}
