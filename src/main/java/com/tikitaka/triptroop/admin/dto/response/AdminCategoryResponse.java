package com.tikitaka.triptroop.admin.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.tikitaka.triptroop.category.domain.entity.Category;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class AdminCategoryResponse {

    private final Long categoryId;
    private final String name;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private final LocalDateTime createdAt;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private final LocalDateTime deletedAt;

    public AdminCategoryResponse(final Category category) {
        this.categoryId = category.getId();
        this.name = category.getName();
        this.createdAt = category.getCreatedAt();
        this.deletedAt = category.getDeletedAt();
    }

}
