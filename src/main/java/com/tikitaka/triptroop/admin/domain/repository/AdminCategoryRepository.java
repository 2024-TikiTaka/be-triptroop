package com.tikitaka.triptroop.admin.domain.repository;

import com.tikitaka.triptroop.category.domain.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminCategoryRepository extends JpaRepository<Category, Long> {

}
