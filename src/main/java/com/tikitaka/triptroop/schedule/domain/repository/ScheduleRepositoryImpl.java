package com.tikitaka.triptroop.schedule.domain.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.tikitaka.triptroop.common.domain.type.Visibility;
import com.tikitaka.triptroop.schedule.domain.entity.Schedule;
import com.tikitaka.triptroop.schedule.dto.response.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.tikitaka.triptroop.image.domain.entity.QImage.image;
import static com.tikitaka.triptroop.place.domain.entity.QPlace.place;
import static com.tikitaka.triptroop.schedule.domain.entity.QSchedule.schedule;
import static com.tikitaka.triptroop.schedule.domain.entity.QScheduleItem.scheduleItem;
import static com.tikitaka.triptroop.schedule.domain.entity.QScheduleParticipant.scheduleParticipant;
import static com.tikitaka.triptroop.user.domain.entity.QProfile.profile;
import static com.tikitaka.triptroop.user.domain.entity.QUser.user;

@Repository
@RequiredArgsConstructor
public class ScheduleRepositoryImpl implements ScheduleRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<Schedule> findSchedulesByKeyword(Visibility visibility, String keyword, String sort, Long area) {
        BooleanExpression predicate = Expressions.asBoolean(true).isTrue();

        if (visibility != null) {
            predicate = predicate.and(schedule.visibility.eq(visibility));
        }

        if (keyword != null && !keyword.isEmpty()) {
            predicate = predicate.and(schedule.title.containsIgnoreCase(keyword));
        }
        if (area != null && area > 0) {
            predicate = predicate.and(schedule.area.id.eq(area));
        }


        JPAQuery<Schedule> query = queryFactory.selectFrom(schedule).where(predicate);


        if (sort != null && !sort.isEmpty()) {
            switch (sort) {
                case "views": // 조회 순
                    query.orderBy(schedule.views.desc());
                    break;
                case "latest": // 최신 순
                    query.orderBy(schedule.createdAt.desc());
                    break;
                // 기본은 최신 순으로 설정
                default:
                    query.orderBy(schedule.createdAt.desc());
                    break;
            }
        } else {
            // 정렬 조건이 주어지지 않은 경우, 최신 순으로 정렬
            query.orderBy(schedule.createdAt.desc());
        }

        // 쿼리 수행 후 결과 반환
        return query.fetch();
    }

    @Override
    public List<ScheduleParticipantProfileResponse> findParticipantsProfilesByScheduleId(Long scheduleId) {
        return queryFactory
                .select(new QScheduleParticipantProfileResponse(
                        scheduleParticipant.id,
                        user.id,
                        profile.nickname,
                        profile.profileImage,
                        profile.mbti,
                        scheduleParticipant.reviewContent,
                        scheduleParticipant.reviewPoint

                ))
                .from(scheduleParticipant)
                .join(user).on(user.id.eq(scheduleParticipant.reviewerId))
                .join(schedule).on(schedule.id.eq(scheduleParticipant.schedule.id))
                .join(profile).on(profile.userId.eq(user.id))
                .where(schedule.id.eq(scheduleId))
                .fetch();
    }

    @Override
    public ScheduleInformationResponse findScheduleById(Long scheduleId) {
        return queryFactory
                .select(new QScheduleInformationResponse(
                        schedule.id,
                        image.path,
                        user.id,
                        schedule.startDate,
                        schedule.endDate,
                        schedule.title,
                        schedule.count,
                        schedule.views,
                        profile.nickname,
                        profile.mbti,
                        profile.profileImage
                ))
                .from(schedule)
                .join(user).on(schedule.userId.eq(user.id))  // user 엔티티와의 조인 추가
                .leftJoin(image).on(schedule.id.eq(image.scheduleId))
                .leftJoin(profile).on(profile.userId.eq(user.id))
                .where(schedule.id.eq(scheduleId).and(schedule.visibility.eq(Visibility.PUBLIC)).and(schedule.isDeleted.eq(false)))
                .fetchFirst();// 첫 번째 결과만 가져옴
    }

    @Override
    public List<ScheduleItemInfoResponse> findScheduleItemByScheduleId(Long scheduleId) {
        return queryFactory
                .select(new QScheduleItemInfoResponse(
                        place.name,
                        place.address,
                        scheduleItem.planDate,
                        scheduleItem.kind,
                        scheduleItem.cost,
                        scheduleItem.content
                ))
                .from(scheduleItem)
                .join(schedule).on(schedule.id.eq(scheduleItem.scheduleId))
                .where(schedule.id.eq(scheduleId))
                .fetch();
    }


//    @Override
//    public List<ScheduleItem> findScheduleItemById(Long scheduleId) {
//        return queryFactory
//                .selectFrom(scheduleItem)
//                .leftJoin(schedule.scheduleItem, scheduleItem) // scheduleItem 필드를 사용하여 조인
//                .where(schedule.id.eq(scheduleId)) // scheduleId에 해당하는 일정만 가져옵니다.
//                .fetch();
//    }


}

