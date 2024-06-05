package com.tikitaka.triptroop.travel.domain.repository;

import com.tikitaka.triptroop.travel.dto.response.ImageTravelResponse;
import com.tikitaka.triptroop.travel.dto.response.TravelResponse;

import java.util.List;

public interface TravelRepositoryCustom {
    TravelResponse findDetailedTravelByIdAndVisibility(Long id);

    List<ImageTravelResponse> findImagesByTravelId(Long id);
}
