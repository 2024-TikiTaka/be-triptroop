package com.tikitaka.triptroop.admin.service;

import com.tikitaka.triptroop.admin.domain.repository.AdminCategoryRepository;
import com.tikitaka.triptroop.admin.dto.request.AdminCategorySaveRequest;
import com.tikitaka.triptroop.admin.dto.response.AdminCategoryResponse;
import com.tikitaka.triptroop.category.domain.entity.Category;
import com.tikitaka.triptroop.common.exception.NotFoundException;
import com.tikitaka.triptroop.common.exception.type.ExceptionCode;
import com.tikitaka.triptroop.image.dto.request.AdminInquiryReplyRequest;
import com.tikitaka.triptroop.inquiry.domain.entity.Inquiry;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

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
    @Transactional
    public Category categorySave(final Long categoryId, final AdminCategorySaveRequest adminCategorySaveRequest) {

        Category category = adminCategoryRepository.findById(categoryId)
                .orElseThrow(() -> new NotFoundException(ExceptionCode.NOT_FOUND_CATEGORY));

        category.addCategory(adminCategorySaveRequest.);
        adminCategoryRepository.save(category);

        return category;
    }


    /* 3. 카테고리 관리 > 카테고리 수정 */

}
