package com.tikitaka.triptroop.travel.domain.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.tikitaka.triptroop.common.domain.type.Visibility;
import com.tikitaka.triptroop.travel.dto.response.ImageTravelResponse;
import com.tikitaka.triptroop.travel.dto.response.QImageTravelResponse;
import com.tikitaka.triptroop.travel.dto.response.QTravelResponse;
import com.tikitaka.triptroop.travel.dto.response.TravelResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.tikitaka.triptroop.image.domain.entity.QImage.image;
import static com.tikitaka.triptroop.place.domain.entity.QPlace.place;
import static com.tikitaka.triptroop.travel.domain.entity.QTravel.travel;
import static com.tikitaka.triptroop.user.domain.entity.QProfile.profile;
import static com.tikitaka.triptroop.user.domain.entity.QUser.user;


@Repository
@RequiredArgsConstructor
public class TravelRepositoryImpl implements TravelRepositoryCustom {


    private final JPAQueryFactory queryFactory;

    @Override
    public TravelResponse findDetailedTravelByIdAndVisibility(Long id) {
        return queryFactory
                .select(new QTravelResponse(
                        travel.title,
                        travel.content,
                        place.address,
                        place.name,
                        profile.nickname,
                        profile.profileImage
//                        travelComment.content
                ))
                .from(travel)
                .join(user).on(travel.userId.eq(user.id))
                .leftJoin(place).on(travel.placeId.eq(place.id))
                .leftJoin(profile).on(profile.userId.eq(user.id))
//                .leftJoin(travelComment).on(travel.id.eq(travelComment.travelId))
//                .leftJoin(travel.place, place)
//                .leftJoin(travel.profile, profile)
//                .leftJoin(travel.comments, travelComment)
                .where(
                        travel.id.eq(id)
                                .and(travel.visibility.eq(Visibility.PUBLIC))
                                .and(travel.isDeleted.eq(false))
                )
                .fetchFirst();
    }

    @Override
    public List<ImageTravelResponse> findImagesByTravelId(Long id) {
        return queryFactory
                .select(new QImageTravelResponse(
                        image.id,
                        image.path,
                        image.uuid
                ))
                .from(image)
                .leftJoin(image).on(travel.id.eq(image.travelId))
                .where(
                        image.travelId.eq(id)

                )
                .fetch();
    }


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

//    @Override
//    public TravelResponse findDetailedTravelByIdAndVisibility(Long id, Visibility visibility) {
////        QTravel travel = QTravel.travel;
////        QImage image = QImage.image;
////        QPlace place = QPlace.place;
////        QProfile profile = QProfile.profile;
////        QTravelComment travelComment = QTravelComment.travelComment;
//
//        TravelResponse travelResponse = queryFactory
//                .select(new QTravelResponse(
//                        travel.title,
//                        travel.content,
//                        image.path,
//                        place.address,
//                        place.name,
//                        profile.nickname,
//                        profile.profileImage,
//                        travelComment.content
//                ))
//                .from(travel)
//                .leftJoin(travel).on(travel.id.eq(image.travelId))
//                .leftJoin(travel).on(travel.placeId.eq(place.id))
//                .leftJoin(profile).on(profile.userId.eq(travelComment.userId))
//                .leftJoin(travel).on(travel.id.eq(travelComment.travelId))
////                .leftJoin(travel.place, place)
////                .leftJoin(travel.profile, profile)
////                .leftJoin(travel.comments, travelComment)
//                .where(travel.id.eq(id)
//                        .and(travel.visibility.eq(visibility)))
//                .fetchOne();
//
//        return Optional.ofNullable(travelResponse);
//    }

}
