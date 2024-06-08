package com.tikitaka.triptroop.admin.controller;

import com.tikitaka.triptroop.admin.dto.response.AdminUserDetailResponse;
import com.tikitaka.triptroop.admin.dto.response.AdminUserResponse;
import com.tikitaka.triptroop.admin.service.AdminUserService;
import com.tikitaka.triptroop.common.dto.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/admin")
public class AdminUserController {

    private final AdminUserService adminUserService;

    /* 1. 관리자 회원 관리 - 회원 목록 조회 */
    @GetMapping("/users")
    public ResponseEntity<ApiResponse> getUserList() {

        final List<AdminUserResponse> adminUserResponses = adminUserService.findAdminUsers();

        return ResponseEntity.ok(ApiResponse.success(adminUserResponses));
    }

    /* 2. 관리자 회원 관리 - 회원 상세 조회 */
    @GetMapping("/users/{userId}")
    public ResponseEntity<ApiResponse> getUserDetail(@PathVariable final Long userId) {

        final AdminUserDetailResponse adminUserDetailResponse = adminUserService.findAdminUserDetail(userId);

        return ResponseEntity.ok(ApiResponse.success(adminUserDetailResponse));
    }

}
