package com.tikitaka.triptroop.travel.domain.repository;

import com.tikitaka.triptroop.common.domain.type.Visibility;
import com.tikitaka.triptroop.travel.domain.entity.Travel;

import java.util.Optional;

public interface TravelRepositoryCustom {
    Optional<Travel> findDetailedTravelByIdAndVisibility(Long id, Visibility visibility);
}
