package com.tikitaka.triptroop.common.domain.repository;

import com.tikitaka.triptroop.common.domain.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> { }
