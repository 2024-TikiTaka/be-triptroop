package com.tikitaka.triptroop.admin.service;

import com.tikitaka.triptroop.admin.domain.repository.AdminCategoryRepository;
import com.tikitaka.triptroop.admin.dto.request.AdminCategorySaveRequest;
import com.tikitaka.triptroop.category.domain.entity.Category;
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
    public List<Category> getCategoryList() {
        return adminCategoryRepository.findAll();
//        return adminCategoryRepository.findAdminCategoryAll();
    }


    /* 2. 카테고리 관리 > 카테고리 등록 */
    @Transactional
    public Long save(final AdminCategorySaveRequest adminCategorySaveRequest) {

        final Category newCategory = Category.of(
                adminCategorySaveRequest.getName()
        );

        final Category category = adminCategoryRepository.save(newCategory);
        return category.getId();
    }


    /* 3. 카테고리 관리 > 카테고리 수정 */


}
