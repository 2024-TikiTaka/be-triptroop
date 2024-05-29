package com.tikitaka.triptroop.report.controller;

import com.tikitaka.triptroop.report.dto.response.ReportTableResponse;
import com.tikitaka.triptroop.report.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class ReportController {

    private final ReportService reportService;

    /* 1. 신고 목록 조회 */
    @GetMapping("/report")
    public ResponseEntity< List<ReportTableResponse> > getReportTest(
            @RequestParam(required = false) final Long reporterId
    ) {

        final List<ReportTableResponse> reportResponse = reportService.getReportTest(reporterId);

        return ResponseEntity.ok(reportResponse);
    }
}