package com.tikitaka.triptroop.admin.controller;


import com.tikitaka.triptroop.common.dto.response.ApiResponse;
import com.tikitaka.triptroop.inquiry.dto.response.InquiryDetailResponse;
import com.tikitaka.triptroop.inquiry.dto.response.InquiryListResponse;
import com.tikitaka.triptroop.inquiry.service.InquiryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/admin/inquiry")
public class AdminInquiryController {

    private final InquiryService inquiryService;

    @GetMapping("")
    public ResponseEntity<ApiResponse> getInquriryList() {
        final List<InquiryListResponse> inquryList = inquiryService.getInquriryList();
        return ResponseEntity.ok(ApiResponse.success("문의 목록 조회에 성공하였습니다.", inquryList));
    }

    @GetMapping("/{inquiryId}")
    public ResponseEntity<ApiResponse> getInquiryDetail(@PathVariable final Long inquiryId) {
        final InquiryDetailResponse inquiryDetail = inquiryService.getInquiryDetail(inquiryId);
        return ResponseEntity.ok(ApiResponse.success("문의 상세 조회에 성공하였습니다.", inquiryDetail));
    }


}
