package com.tikitaka.triptroop.place.domain.repository;

import com.tikitaka.triptroop.place.domain.entity.Place;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PlaceRepository extends JpaRepository<Place, Long> {

    Optional<Place> findById(Long id);


}
