package com.tikitaka.triptroop.admin.service;

import com.tikitaka.triptroop.admin.domain.repository.AdminCategoryRepository;
import com.tikitaka.triptroop.admin.dto.response.AdminCategoryResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class AdminCategoryService {

    private final AdminCategoryRepository adminCategoryRepository;

    /* 1. 카테고리 관리 > 카테고리 목록 조회 */
    @Transactional(readOnly = true)
    public List<AdminCategoryResponse> getCategoryList() {
        return adminCategoryRepository.findAdminCategoryAll();
    }


    /* 2. 카테고리 관리 > 카테고리 등록 */


    /* 3. 카테고리 관리 > 카테고리 수정 */

}
