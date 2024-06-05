package com.tikitaka.triptroop.travel.domain.repository;

import com.tikitaka.triptroop.travel.dto.response.TravelResponse;

public interface TravelRepositoryCustom {
    TravelResponse findDetailedTravelByIdAndVisibility(Long id);
}
