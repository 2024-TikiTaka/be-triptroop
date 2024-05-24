package com.tikitaka.triptroop.schedule.domain.repository;

import com.tikitaka.triptroop.schedule.domain.entity.ScheduleItem;
import org.springframework.data.jpa.repository.JpaRepository;

/* Entity에는 Entity명 적기 */
public interface ScheduleItemRepository extends JpaRepository<ScheduleItem, Long> {

    /* Repository 앞에 부분을 지우고 본인의 기능 폴더명을 적어주세요. */
}
