package com.tikitaka.triptroop.travel.service;

import com.tikitaka.triptroop.travel.domain.entity.Travel;
import com.tikitaka.triptroop.travel.domain.repository.TravelRepository;
import com.tikitaka.triptroop.travel.domain.type.VisibleStatus;
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
    public Page<TravelResponse> findAll(final Integer page ){

        Page<Travel> travels = null;
        travels = travelRepository.findByStatus(getPageable(page), VisibleStatus.PUBLIC);


        return travels.map(TravelResponse::from);
    };



}
