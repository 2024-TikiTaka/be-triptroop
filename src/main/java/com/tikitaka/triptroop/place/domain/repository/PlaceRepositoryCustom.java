package com.tikitaka.triptroop.place.domain.repository;

import com.tikitaka.triptroop.place.dto.response.PlaceTravelResponse;

public interface PlaceRepositoryCustom {

    PlaceTravelResponse findById(Long id);
}
