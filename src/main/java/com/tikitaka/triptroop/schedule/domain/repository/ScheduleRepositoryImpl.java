package com.tikitaka.triptroop.schedule.domain.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.tikitaka.triptroop.common.domain.type.Visibility;
import com.tikitaka.triptroop.schedule.domain.entity.Schedule;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.tikitaka.triptroop.schedule.domain.entity.QSchedule.schedule;

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

//    @Override
//    public List<ScheduleItem> findScheduleItemById(Long scheduleId) {
//        return queryFactory
//                .selectFrom(scheduleItem)
//                .leftJoin(schedule.scheduleItem, scheduleItem) // scheduleItem 필드를 사용하여 조인
//                .where(schedule.id.eq(scheduleId)) // scheduleId에 해당하는 일정만 가져옵니다.
//                .fetch();
//    }


}

