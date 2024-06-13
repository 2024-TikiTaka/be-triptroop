package com.tikitaka.triptroop.place.service;

import com.tikitaka.triptroop.place.domain.entity.Place;
import com.tikitaka.triptroop.place.domain.repository.PlaceRepository;
import com.tikitaka.triptroop.schedule.domain.repository.ScheduleItemRepository;
import com.tikitaka.triptroop.schedule.dto.request.ScheduleItemCreateRequest;
import com.tikitaka.triptroop.schedule.dto.request.ScheduleItemUpdateRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class PlaceService { //<- Service 앞의 tt 부분을 변경한 본인의 폴더명으로 바꿔주세요.

    private final PlaceRepository placeRepository;
    private final ScheduleItemRepository scheduleItemRepository;

    public Long savePlace(List<ScheduleItemCreateRequest> scheduleItemRequests) {
        Long placeId = null;

        for (ScheduleItemCreateRequest request : scheduleItemRequests) {
            // ScheduleItemCreateRequest에서 필요한 정보 추출
            String address = request.getAddress();
            String name = request.getName();

            // Place 객체 생성 및 저장
            Place newPlace = new Place(address, name);
            Place savedPlace = placeRepository.save(newPlace);

            // 저장된 Place의 ID를 반환
            placeId = savedPlace.getId();
        }

        return placeId;
    }

    public void updatePlace(ScheduleItemUpdateRequest scheduleItemUpdateRequest, Long placeId) {
        Place place = placeRepository.findById(placeId).get();
        place.update(
                placeId,
                scheduleItemUpdateRequest.getAddress(),
                scheduleItemUpdateRequest.getName()
        );
        log.info("@!#@!#@!#@!#@!#@!#!@#!@# : {}", place.toString());

    }
}

