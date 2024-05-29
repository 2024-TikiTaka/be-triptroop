package com.tikitaka.triptroop.travel.domain.repository;

import org.springframework.stereotype.Repository;

@Repository
public class TravelRepositoryImpl implements TravelRepositoryCustom {

//    @Autowired
//    private JPAQueryFactory queryFactory;
//
//    @Override
//    public Optional<Travel> findDetailedTravelByIdAndVisibility(Long id, String visibility) {
//        Travel fetchedTravel = queryFactory
//                .selectFrom(travel)
//                .leftJoin(travel.userId, user.id).fetchJoin()
//                .leftJoin(travel.travelcomment, travelComment).fetchJoin()
//                .leftJoin(travel.place, place).fetchJoin()
//                .where(
//                        travel.id.eq(id),
//                        travel.visibility.eq(visibility)
//                )
//                .fetchOne();
//
//        return Optional.ofNullable(fetchedTravel);
//    }

}
