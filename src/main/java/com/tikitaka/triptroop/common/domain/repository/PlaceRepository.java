package com.tikitaka.triptroop.common.domain.repository;

import com.tikitaka.triptroop.common.domain.entity.Place;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaceRepository extends JpaRepository<Place, Long> { }
