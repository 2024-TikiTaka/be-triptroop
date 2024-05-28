package com.tikitaka.triptroop.image.domain.repository;

import com.tikitaka.triptroop.image.domain.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImageRepository extends JpaRepository<Image, Long> {


    List<Image> findByTravelId(Long travelId);
}
