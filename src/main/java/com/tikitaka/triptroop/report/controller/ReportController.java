package com.tikitaka.triptroop.report.controller;

import com.tikitaka.triptroop.report.dto.response.ReportTableResponse;
import com.tikitaka.triptroop.report.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/reports")
public class ReportController {

    private final ReportService reportService;

    /* 1. 신고 목록 조회 */
    @GetMapping("/{reporter}")
    public ResponseEntity< List<ReportTableResponse> > getReportTest(
            @PathVariable final Long reporter

    ) {

        final List<ReportTableResponse> reportResponse = reportService.getReportTest(reporter);

        return ResponseEntity.ok(reportResponse);
    }
}
