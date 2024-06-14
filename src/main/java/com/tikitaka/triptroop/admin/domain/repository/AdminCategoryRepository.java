package com.tikitaka.triptroop.admin.domain.repository;

import com.tikitaka.triptroop.admin.dto.response.AdminCategoryResponse;
import com.tikitaka.triptroop.category.domain.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AdminCategoryRepository extends JpaRepository<Category, Long> {

    @Query("SELECT new com.tikitaka.triptroop.admin.dto.response.AdminCategoryResponse(c)" +
            "FROM Category c ")
    List<AdminCategoryResponse> findAdminCategoryAll();


}
