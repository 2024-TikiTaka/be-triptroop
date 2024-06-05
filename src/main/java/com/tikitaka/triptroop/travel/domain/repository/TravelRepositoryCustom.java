package com.tikitaka.triptroop.travel.domain.repository;

import com.tikitaka.triptroop.travel.dto.response.ImageTravelResponse;
import com.tikitaka.triptroop.travel.dto.response.TravelResponse;

import java.util.List;

public interface TravelRepositoryCustom {
    /* 게시글 조회 */
    TravelResponse findDetailedTravelByIdAndVisibility(Long id);

    /* 이미지 조회*/
    List<ImageTravelResponse> findImagesByTravelId(Long id);
}
