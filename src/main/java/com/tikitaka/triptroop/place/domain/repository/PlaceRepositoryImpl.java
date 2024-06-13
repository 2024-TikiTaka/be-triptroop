package com.tikitaka.triptroop.place.domain.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.tikitaka.triptroop.place.dto.response.PlaceTravelResponse;
import com.tikitaka.triptroop.place.dto.response.QPlaceTravelResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import static com.tikitaka.triptroop.place.domain.entity.QPlace.place;
import static com.tikitaka.triptroop.travel.domain.entity.QTravel.travel;

@Repository
@RequiredArgsConstructor
public class PlaceRepositoryImpl implements PlaceRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public PlaceTravelResponse findById(Long id) {
        return queryFactory
                .select(new QPlaceTravelResponse(
                        place.address,
                        place.name

                ))
                .from(place)
                .join(travel).on(travel.placeId.eq(place.id))
                .where(travel.placeId.eq(id))
                .fetchFirst();

    }
}
