package com.tikitaka.triptroop.place.service;

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

    private final PlaceRepository placeRepository;

    public Long savePlace(ScheduleItemCreateRequest scheduleItemRequest) {
        final Place newPlace = Place.save(
                scheduleItemRequest.getAddress(),
                scheduleItemRequest.getName()
        );
        final Place place = placeRepository.save(newPlace);
        return place.getId();
    }
//    public Long saveItem(ScheduleItemCreateRequest scheduleItemRequest, Long id) {
//        final ScheduleItem newItem = ScheduleItem.of(
//                id,
//                scheduleItemRequest.getPlaceId(),
//                scheduleItemRequest.getContent(),
//                scheduleItemRequest.getCost(),
//                scheduleItemRequest.getPlanDate(),
//                scheduleItemRequest.getKind()
//        );
//        final ScheduleItem scheduleItem = scheduleItemRepository.save(newItem);
//        return scheduleItem.getId();
//    }
    /* 내용을 작성해주세요. */

}
