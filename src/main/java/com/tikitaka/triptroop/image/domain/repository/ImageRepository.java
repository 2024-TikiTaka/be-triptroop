package com.tikitaka.triptroop.image.domain.repository;

import com.tikitaka.triptroop.image.domain.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Long> {


}