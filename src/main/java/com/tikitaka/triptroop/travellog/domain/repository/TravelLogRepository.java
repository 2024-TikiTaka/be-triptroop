package com.tikitaka.triptroop.travellog.domain.repository;

import com.tikitaka.triptroop.travellog.domain.entity.TravelLog;
import org.springframework.data.jpa.repository.JpaRepository;

/* Entity에는 Entity명 적기 */
public interface TravelLogRepository extends JpaRepository<TravelLog, Long> {

    /* Repository 앞에 부분을 지우고 본인의 기능 폴더명을 적어주세요. */

}
