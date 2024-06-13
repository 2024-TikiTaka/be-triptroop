package com.tikitaka.triptroop.place.service;

import com.tikitaka.triptroop.place.domain.entity.Place;
import com.tikitaka.triptroop.place.domain.repository.PlaceRepository;
import com.tikitaka.triptroop.place.domain.repository.PlaceRepositoryImpl;
import com.tikitaka.triptroop.place.dto.response.PlaceInfoResponse;
import com.tikitaka.triptroop.place.dto.response.PlaceTravelResponse;
import com.tikitaka.triptroop.travel.dto.request.TravelRequest;
import com.tikitaka.triptroop.place.domain.entity.Place;
import com.tikitaka.triptroop.place.domain.repository.PlaceRepository;
import com.tikitaka.triptroop.schedule.dto.request.ScheduleItemCreateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class PlaceService { //<- Service 앞의 tt 부분을 변경한 본인의 폴더명으로 바꿔주세요.

    private final PlaceRepositoryImpl placeRepositoryImpl;
    private final PlaceRepository placeRepository;

    public PlaceInfoResponse findPlace(Long travelId) {

        PlaceTravelResponse placeTravelResponse = placeRepositoryImpl.findPlaceById(travelId);

        return PlaceInfoResponse.of(placeTravelResponse);
    }


    public Long savePlace(ScheduleItemCreateRequest scheduleItemRequest) {
        final Place newPlace = Place.save(
                scheduleItemRequest.getAddress(),
                scheduleItemRequest.getName()
        );
        final Place place = placeRepository.save(newPlace);
        return place.getId();
    }

    public Long saveplace(TravelRequest travelRequest) {
        final Place newPlace = Place.insert(
                travelRequest.getAddress(),
                travelRequest.getName()
        );
        final Place place = placeRepository.save(newPlace);
        return place.getId();
    }

    ;
}
