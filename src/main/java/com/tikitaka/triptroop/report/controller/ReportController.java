package com.tikitaka.triptroop.report.controller;

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
    @GetMapping("/{reporterId}")
    public ResponseEntity<List<ReportTableResponse>> getReport(
            @PathVariable final Long reporterId

    ) {

        final List<ReportTableResponse> reportResponse = reportService.getReport(reporterId);

        return ResponseEntity.ok(reportResponse);
    }
}
