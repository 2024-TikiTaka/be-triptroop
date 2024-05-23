package com.tikitaka.triptroop.travel.service;

import com.tikitaka.triptroop.common.domain.type.Visibility;
import com.tikitaka.triptroop.travel.domain.entity.Travel;
import com.tikitaka.triptroop.travel.domain.repository.TravelRepository;
import com.tikitaka.triptroop.travel.dto.response.TravelResponse;
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
public class TravelService { //<- Service 앞의 tt 부분을 변경한 본인의 폴더명으로 바꿔주세요.

    /* 내용을 작성해주세요. */
    private final TravelRepository travelRepository;

    private Pageable getPageable(final Integer page) {
        return PageRequest.of(page - 1, 10, Sort.by("id").descending());
    }


    /* 공개 게시글 조회 */
    @Transactional(readOnly = true)
    public Page<TravelResponse> findByAll(final Integer page, final Long categoryId, final Long areaId) {


        Page<Travel> travels = null;
        if (areaId != null && areaId > 0) {
            travels = travelRepository.findByAreaIdAndVisibility(getPageable(page), areaId, Visibility.PUBLIC);
        } else if (categoryId != null && categoryId > 0) {
            travels = travelRepository.findByCategoryIdAndVisibility(getPageable(page), categoryId, Visibility.PUBLIC);
        }


        return travels.map(TravelResponse::from);
    }


}
