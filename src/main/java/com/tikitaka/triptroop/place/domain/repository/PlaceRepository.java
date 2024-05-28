package com.tikitaka.triptroop.place.domain.repository;

import com.tikitaka.triptroop.place.domain.entity.Place;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaceRepository extends JpaRepository<Place, Long> {
}
