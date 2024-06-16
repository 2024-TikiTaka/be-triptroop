package com.tikitaka.triptroop.admin.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.tikitaka.triptroop.category.domain.entity.Category;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
public class AdminCategoryResponse {

    private final Long categoryId;
    private final String name;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private final LocalDateTime createdAt;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private final LocalDateTime deletedAt;

    public static AdminCategoryResponse form(final Category category) {
        return new AdminCategoryResponse(
                category.getId(),
                category.getName(),
                category.getCreatedAt(),
                category.getDeletedAt()
        );
    }
}
