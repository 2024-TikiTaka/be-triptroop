package com.tikitaka.triptroop.place.service;

import com.tikitaka.triptroop.place.domain.repository.PlaceRepositoryImpl;
import com.tikitaka.triptroop.place.dto.response.PlaceInfoResponse;
import com.tikitaka.triptroop.place.dto.response.PlaceTravelResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class PlaceService { //<- Service 앞의 tt 부분을 변경한 본인의 폴더명으로 바꿔주세요.

    private final PlaceRepositoryImpl placeRepositoryImpl;

    public PlaceInfoResponse findPlace(Long travelId) {

        PlaceTravelResponse placeTravelResponse = placeRepositoryImpl.findPlaceById(travelId);

        return PlaceInfoResponse.of(placeTravelResponse);
    }
}
