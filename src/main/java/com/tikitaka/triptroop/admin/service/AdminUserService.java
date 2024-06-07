package com.tikitaka.triptroop.admin.service;

import com.tikitaka.triptroop.admin.domain.AdminUserRepository;
import com.tikitaka.triptroop.admin.dto.response.AdminUserDetailResponse;
import com.tikitaka.triptroop.admin.dto.response.AdminUserResponse;
import com.tikitaka.triptroop.common.exception.NotFoundException;
import com.tikitaka.triptroop.common.exception.type.ExceptionCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class AdminUserService {

    private final AdminUserRepository adminUserRepository;

    /* 1. 관리자 회원 관리 - 회원 목록 조회 */
    @Transactional(readOnly = true)
    public List<AdminUserResponse> findAdminUsers() {

        return adminUserRepository.findAdminUsersAll();

    }

    /* 2. 관리자 회원 관리 - 회원 상세 조회 */
    @Transactional(readOnly = true)
    public AdminUserDetailResponse findAdminUserDetail(final Long userId) {

        return adminUserRepository.findAdminUserDetailByUserId(userId).orElseThrow(() -> new NotFoundException(ExceptionCode.NOT_FOUND_USER));

    }
}
