package com.tikitaka.triptroop.admin.controller;

import com.tikitaka.triptroop.admin.dto.response.AdminReportResponse;
import com.tikitaka.triptroop.admin.service.AdminReportService;
import com.tikitaka.triptroop.common.dto.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/admin/report")
public class AdminReportController {

    private final AdminReportService adminReportService;

    @GetMapping("")
    public ResponseEntity<ApiResponse> getReportList() {
        final List<AdminReportResponse> reportList = adminReportService.getReportList();
        return ResponseEntity.ok(ApiResponse.success("신고 목록 조회에 성공하였습니다.", reportList));
    }


}
