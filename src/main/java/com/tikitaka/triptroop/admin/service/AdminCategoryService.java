package com.tikitaka.triptroop.admin.service;

import com.tikitaka.triptroop.admin.domain.repository.AdminCategoryRepository;
import com.tikitaka.triptroop.admin.dto.request.AdminCategorySaveRequest;
import com.tikitaka.triptroop.admin.dto.response.AdminCategoryResponse;
import com.tikitaka.triptroop.category.domain.entity.Category;
import com.tikitaka.triptroop.common.exception.NotFoundException;
import com.tikitaka.triptroop.common.exception.type.ExceptionCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class AdminCategoryService {

    private final AdminCategoryRepository adminCategoryRepository;

    /* 1. 카테고리 관리 > 카테고리 목록 조회 */
    @Transactional(readOnly = true)
    public List<AdminCategoryResponse> getCategoryList() {
        List<Category> categoryList = adminCategoryRepository.findAll();
        return categoryList.stream().map(AdminCategoryResponse::form).collect(Collectors.toList());
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
    @Transactional
    public Category update(final Long categoryId, final AdminCategorySaveRequest adminCategorySaveRequest) {
        Category category = adminCategoryRepository.findById(categoryId)
                .orElseThrow(() -> new NotFoundException(ExceptionCode.NOT_FOUND_CATEGORY));

        category.updateCategory(adminCategorySaveRequest.getName());
        adminCategoryRepository.save(category);
        return category;
    }


}
