package com.tikitaka.triptroop.schedule.domain.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.tikitaka.triptroop.common.domain.type.Visibility;
import com.tikitaka.triptroop.schedule.domain.entity.Schedule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.tikitaka.triptroop.schedule.domain.entity.QSchedule.schedule;

@Repository
public class ScheduleRepositoryImpl implements ScheduleRepositoryCustom {

    @Autowired
    private JPAQueryFactory queryFactory;

//    @Override
//    public List<Schedule> findSchedulesByKeyword(Visibility visibility, String keyword, String sort) {
//        // 모든 스케줄을 검색하는 기본 predicate 생성
//        BooleanExpression predicate = Expressions.asBoolean(true).isTrue();
//
//        // visibility 파라미터가 null이 아닌 경우에만 predicate에 조건 추가
//        if (visibility != null) {
//            predicate = predicate.and(schedule.visibility.eq(visibility));
//        }
//
////        // 검색 키워드가 주어진 경우에만 predicate에 조건 추가
////        if (keyword != null && !keyword.isEmpty()) {
////            predicate = predicate.and(schedule.title.containsIgnoreCase(keyword));
////        }
//        JPAQuery<Schedule> query = queryFactory.selectFrom(schedule).where(predicate);
//
//        // 검색어와 정렬 조건이 함께 주어진 경우에 대한 처리
//        if ((keyword == null || keyword.isEmpty()) && (sort == null || sort.isEmpty())) {
//            // 파라미터가 아무것도 주어지지 않은 경우, 최신 순으로 정렬하여 조회
//            return query.clone().orderBy(schedule.createdAt.desc()).fetch();
//        } else if (keyword != null && !keyword.isEmpty() && (sort == null || sort.isEmpty())) {
//            // keyword만 주어진 경우, keyword가 포함된 제목을 가진 리스트 조회
//            return query.clone().fetch().stream()
//                    .filter(schedule -> schedule.getTitle().toLowerCase().contains(keyword.toLowerCase()))
//                    .collect(Collectors.toList());
//        } else {
//            switch (sort) {
//                case "views": // 조회 순
//                    return query.clone().orderBy(schedule.views.desc()).fetch();
//                case "latest": // 최신 순
//                    return query.clone().orderBy(schedule.createdAt.desc()).fetch();
//                default: // 기본은 조회 순으로 설정
//                    return query.clone().orderBy(schedule.views.desc()).fetch();
//            }
//        }
//    }

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


}

