package com.tikitaka.triptroop.admin.controller;


import com.tikitaka.triptroop.admin.dto.request.AdminNoticeRequest;
import com.tikitaka.triptroop.admin.dto.response.AdminNoticeDetailResponse;
import com.tikitaka.triptroop.admin.dto.response.AdminNoticeResponse;
import com.tikitaka.triptroop.admin.service.AdminNoticeService;
import com.tikitaka.triptroop.common.dto.response.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/admin/notice")
public class AdminNoticeController {

    private final AdminNoticeService adminNoticeService;

    /* 1. 공지 관리 > 공지 목록 조회 */
    @GetMapping("")
    public ResponseEntity<ApiResponse> getNoticeList() {
        final List<AdminNoticeResponse> noticeList = adminNoticeService.getNoticeList();
        return ResponseEntity.ok(ApiResponse.success("공지 목록 조회에 성공하였습니다.", noticeList));
    }

    /* 2. 공지 관리 > 공지 상세 조회 */
    @GetMapping("/{noticeId}")
    public ResponseEntity<ApiResponse> getNoticeDetail(@PathVariable final Long noticeId) {
        final AdminNoticeDetailResponse noticeDetail = adminNoticeService.getNoticeDetail(noticeId);
        return ResponseEntity.ok(ApiResponse.success("공지 상세 조회에 성공하였습니다.", noticeDetail));
    }

    /* 3. 공지 관리 > 공지 등록 */
    @PostMapping("")
    public ResponseEntity<ApiResponse> saveNotice(
            @RequestPart @Valid final AdminNoticeRequest adminNoticeRequest,
            @RequestPart(required = false) final List<MultipartFile> images
    ) {
        final Long noticeId = adminNoticeService.save(adminNoticeRequest, images);
        return ResponseEntity.ok(ApiResponse.success("공지 등록에 성공하였습니다.", noticeId));
    }
}
