package com.tikitaka.triptroop.admin.controller;

import com.tikitaka.triptroop.common.dto.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/admin")
public class AdnimUserController {

    /* 1. 관리자 회원 관리 - 회원 목록 조회 */
    @GetMapping("/users")
    public ResponseEntity<ApiResponse> getUserList() {

        //final List<ReportTableResponse> reportResponse = reportService.getReport(nickname, kind);

        return null;//ResponseEntity.ok(ApiResponse.success(reportResponse));
    }


}
