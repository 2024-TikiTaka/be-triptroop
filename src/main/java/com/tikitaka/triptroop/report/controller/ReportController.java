package com.tikitaka.triptroop.report.controller;

import com.tikitaka.triptroop.common.dto.response.ApiResponse;
import com.tikitaka.triptroop.report.dto.request.ReportRequest;
import com.tikitaka.triptroop.report.dto.response.ReportDetailResponse;
import com.tikitaka.triptroop.report.dto.response.ReportTableResponse;
import com.tikitaka.triptroop.report.service.ReportService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/reports")
public class ReportController {

    private final ReportService reportService;

    /* 1. 신고 목록 조회 Test */
    @GetMapping("/{kind}/{nickname}")
    public ResponseEntity<ApiResponse> getReport(@PathVariable final String kind, @PathVariable final String nickname) {

        final List<ReportTableResponse> reportResponse = reportService.getReport(nickname, kind);

        return ResponseEntity.ok(ApiResponse.success(reportResponse));
    }

    /* 2. 신고 상세 조회 Test */
    @GetMapping("/{reportId}")
    public ResponseEntity<ApiResponse> getReportDetail(@PathVariable final Long reportId) {

        final ReportDetailResponse reportDetailResponse = reportService.getReportDetail(reportId);

        return ResponseEntity.ok(ApiResponse.success(reportDetailResponse));
    }

    /* 3. 신고 등록 Test */

    @PostMapping()
    public ResponseEntity<ApiResponse<Long>> saveReport(
            @RequestPart @Valid final ReportRequest reportRequest,
            @RequestPart final List<MultipartFile> images
    ) {

        final Long reportId = reportService.save(reportRequest, 6L, images);

        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.success("신고가 접수되었습니다.", reportId));
    }
}
