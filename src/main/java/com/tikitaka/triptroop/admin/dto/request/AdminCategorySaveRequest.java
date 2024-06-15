package com.tikitaka.triptroop.admin.dto.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class AdminCategorySaveRequest {

    private Long categoryId;
    private String name;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime deletedAt;

    @JsonCreator
    public AdminCategorySaveRequest(
            @JsonProperty("categoryId") Long categoryId
    ) {
        this.categoryId = categoryId;
    }

}
