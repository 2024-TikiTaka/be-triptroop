package com.tikitaka.triptroop.travelLong.domain.repository;

import com.tikitaka.triptroop._example.domain.entity.Example;
import org.springframework.data.jpa.repository.JpaRepository;

/* Entity에는 Entity명 적기 */
public interface TravelLogRepository extends JpaRepository<Example, Long> {

    /* Repository 앞에 부분을 지우고 본인의 기능 폴더명을 적어주세요. */

}
