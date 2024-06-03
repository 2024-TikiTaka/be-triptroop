package com.tikitaka.triptroop.report.controller;

import com.tikitaka.triptroop.report.dto.response.ReportDetailResponse;
import com.tikitaka.triptroop.report.dto.response.ReportTableResponse;
import com.tikitaka.triptroop.report.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/reports")
public class ReportController {

    private final ReportService reportService;

    /* 1. 신고 목록 조회 Test */
    @GetMapping("/{kind}/{nickname}")
    public ResponseEntity<List<ReportTableResponse>> getReport(@PathVariable final String kind, @PathVariable final String nickname) {

        final List<ReportTableResponse> reportResponse = reportService.getReport(nickname, kind);

        return ResponseEntity.ok(reportResponse);
    }

    /* 2. 신고 상세 조회 Test */
    @GetMapping("/{reportId}")
    public ResponseEntity<ReportDetailResponse> getReportDetail(@PathVariable final Long reportId) {

        final ReportDetailResponse reportDetailResponse = reportService.getReportDetail(reportId);

        return ResponseEntity.ok(reportDetailResponse);
    }
}
