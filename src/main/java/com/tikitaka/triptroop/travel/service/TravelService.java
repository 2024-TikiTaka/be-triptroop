package com.tikitaka.triptroop.travel.service;


import com.tikitaka.triptroop.common.domain.repository.AreaRepository;
import com.tikitaka.triptroop.common.domain.repository.CategoryRepository;
import com.tikitaka.triptroop.common.domain.type.Visibility;
import com.tikitaka.triptroop.common.exception.NotFoundException;
import com.tikitaka.triptroop.common.exception.type.ExceptionCode;
import com.tikitaka.triptroop.place.domain.repository.PlaceRepository;
import com.tikitaka.triptroop.travel.domain.entity.Travel;
import com.tikitaka.triptroop.travel.domain.repository.TravelRepository;
import com.tikitaka.triptroop.travel.dto.request.TravelRequest;
import com.tikitaka.triptroop.travel.dto.response.TravelResponse;
import com.tikitaka.triptroop.travel.dto.response.TravelsResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class TravelService {

    private final TravelRepository travelRepository;

    private final CategoryRepository categoryRepository;

    private final AreaRepository areaRepository;

    private final PlaceRepository placeRepository;


    private Pageable getPageable(final Integer page) {
        return PageRequest.of(page - 1, 10, Sort.by("id").descending());
    }

    /* 여행지 소개 조회 */
    @Transactional(readOnly = true)
    public Page<TravelsResponse> findAll(final Integer page, final Long categoryId, final Long areaId, final String title) {

        Page<Travel> travels = null;
        if (areaId != null && areaId > 0) {
            travels = travelRepository.findByAreaIdAndVisibility(getPageable(page), areaId, Visibility.PUBLIC);
        } else if (categoryId != null && categoryId > 0) {
            travels = travelRepository.findByCategoryIdAndVisibility(getPageable(page), categoryId, Visibility.PUBLIC);
        } else if (title != null && !title.isEmpty()) {
            travels = travelRepository.findByTitleAndVisibility(getPageable(page), title, Visibility.PUBLIC);
        } else {
            travels = travelRepository.findByVisibility(getPageable(page), Visibility.PUBLIC);
        }


        return travels.map(TravelsResponse::from);
    }

    /* 여행지 소개 상세 조회*/
    @Transactional(readOnly = true)
    public TravelResponse findByTravelId(final Long id) {

        Travel travel = travelRepository.findByIdAndVisibility(id, Visibility.PUBLIC)
                .orElseThrow(() -> new NotFoundException(ExceptionCode.NOT_FOUND_TRAVEL_CODE));

        return TravelResponse.from(travel);
    }


    /* 여행지 소개 등록 */
    public Long save(final TravelRequest travelRequest, final Long userId) {

//        Category category = categoryRepository.findById(travelRequest.getCategoryId())
//                .orElseThrow(() -> new NotFoundException(ExceptionCode.NOT_FOUND_CATEGORY_CODE));
//        Area area = areaRepository.findById(travelRequest.getAreaId())
//                .orElseThrow(() -> new NotFoundException(ExceptionCode.NOT_FOUND_AREA_CODE));
//        Place place = placeRepository.findById(travelRequest.getPlaceId())
//                .orElseThrow(() -> new NotFoundException(ExceptionCode.NOT_FOUND_PLACE_CODE));

        final Travel newTravel = Travel.of(
                userId,
                travelRequest.getCategoryId(),
                travelRequest.getAreaId(),
                travelRequest.getPlaceId(),
                travelRequest.getTitle(),
                travelRequest.getContent()
        );
        final Travel travel = travelRepository.save(newTravel);

        return travel.getId();
    }
}
