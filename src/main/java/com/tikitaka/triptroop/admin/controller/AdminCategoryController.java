package com.tikitaka.triptroop.admin.controller;

import com.tikitaka.triptroop.admin.dto.request.AdminCategorySaveRequest;
import com.tikitaka.triptroop.admin.dto.response.AdminCategoryResponse;
import com.tikitaka.triptroop.admin.service.AdminCategoryService;
import com.tikitaka.triptroop.category.domain.entity.Category;
import com.tikitaka.triptroop.common.dto.response.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/admin/category")
public class AdminCategoryController {

    private final AdminCategoryService adminCategoryService;

    /* 1. 카테고리 관리 > 카테고리 목록 조회 */
    @GetMapping("")
    public ResponseEntity<ApiResponse<?>> getCategoryList() {
        final List<AdminCategoryResponse> categoryList = adminCategoryService.getCategoryList();
        return ResponseEntity.ok(ApiResponse.success("카테고리 목록 조회에 성공하였습니다.", categoryList));
    }

    /* 2. 카테고리 관리 > 카테고리 등록 */
    @PostMapping()
    public ResponseEntity<ApiResponse<Long>> saveCategory(
            @RequestPart @Valid final AdminCategorySaveRequest adminCategorySaveRequest
    ) {
        final Long categoryId = adminCategoryService.save(adminCategorySaveRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.success("카테고리가 등록되었습니다.", categoryId));
    }


    /* 3. 카테고리 관리 > 카테고리 수정 */
    @PatchMapping("/{categoryId}")
    public ResponseEntity<ApiResponse> updateCategory(
            @PathVariable final Long categoryId,
            @RequestPart @Valid final AdminCategorySaveRequest adminCategorySaveRequest
    ) {
        final Category updateCategory = adminCategoryService.update(categoryId, adminCategorySaveRequest);
        return ResponseEntity.ok(ApiResponse.success("카테고리가 수정되었습니다.", updateCategory));
    }
}
