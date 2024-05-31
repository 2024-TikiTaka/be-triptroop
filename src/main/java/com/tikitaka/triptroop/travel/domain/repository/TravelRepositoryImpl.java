package com.tikitaka.triptroop.travel.domain.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
public class TravelRepositoryImpl {

    @Autowired
    private JPAQueryFactory queryFactory;

//    @Override
//    public Optional<Travel> findDetailedTravelByIdAndVisibility(Long id, Visibility visibility) {
//        Travel fetchedTravel=  queryFactory
//                .selectFrom(QTravel.travel)
//                .leftJoin(QTravel.travel.userId, QUser.user).fetchJoin()
//                .leftJoin(QTravel.travel.travelcomment, QTravelComment.travelComment).fetchJoin()
//                .leftJoin(QTravel.travel.place, QPlace.place).fetchJoin()
//                .where(
//                        QTravel.travel.id.eq(id),
//                        QTravel.travel.visibility.eq(String.valueOf(visibility)
//                ).
//
//        return Optional.ofNullable(fetchedTravel);
//    }

}
