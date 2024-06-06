package com.tikitaka.triptroop.admin.service;

import com.tikitaka.triptroop.admin.domain.AdminUserRepository;
import com.tikitaka.triptroop.admin.dto.response.AdminUserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class AdminUserService {

    private final AdminUserRepository adminUserRepository;

    @Transactional(readOnly = true)
    public List<AdminUserResponse> findAdminUsers() {

        return adminUserRepository.findAdminUsersAll();

    }
}
