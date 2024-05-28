package com.tikitaka.triptroop.schedule.domain.repository;

import org.springframework.stereotype.Repository;

// import static com.tikitaka.triptroop.schedule.domain.entity.QSchedule.schedule;

@Repository
public class ScheduleRepositoryImpl {
    //
    // @Autowired
    // private JPAQueryFactory queryFactory;
    //
    // @Override
    // public List<Schedule> findSchedulesByKeyword(Visibility visibility, String keyword, String sort) {
    //     // 모든 스케줄을 검색하는 기본 predicate 생성
    //     BooleanExpression predicate = Expressions.asBoolean(true).isTrue();
    //
    //     // visibility 파라미터가 null이 아닌 경우에만 predicate에 조건 추가
    //     if (visibility != null) {
    //         predicate = predicate.and(schedule.visibility.eq(visibility));
    //     }
    //
    //     // 검색 키워드가 주어진 경우에만 predicate에 조건 추가
    //     if (keyword != null && !keyword.isEmpty()) {
    //         predicate = predicate.and(schedule.title.containsIgnoreCase(keyword));
    //     }
    //     JPAQuery<Schedule> query = queryFactory.selectFrom(schedule).where(predicate);
    //
    //     // 검색어와 정렬 조건이 함께 주어진 경우에 대한 처리
    //     if ((keyword == null || keyword.isEmpty()) && (sort == null || sort.isEmpty())) {
    //         // 파라미터가 아무것도 주어지지 않은 경우, 최신 순으로 정렬하여 조회
    //         return query.clone().orderBy(schedule.createdAt.desc()).fetch();
    //     } else if (keyword != null && !keyword.isEmpty() && (sort == null || sort.isEmpty())) {
    //         // keyword만 주어진 경우, keyword가 포함된 제목을 가진 리스트 조회
    //         return query.clone().fetch().stream()
    //                 .filter(schedule -> schedule.getTitle().contains(keyword))
    //                 .collect(Collectors.toList());
    //     } else {
    //         switch (sort) {
    //             case "views": // 조회 순
    //                 return query.clone().orderBy(schedule.views.desc()).fetch();
    //             case "latest": // 최신 순
    //                 return query.clone().orderBy(schedule.createdAt.desc()).fetch();
    //             default: // 기본은 조회 순으로 설정
    //                 return query.clone().orderBy(schedule.views.desc()).fetch();
    //         }
    //     }
    // }
    // }

}

