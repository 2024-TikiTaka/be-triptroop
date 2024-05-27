package com.tikitaka.triptroop.report.domain.repository;

import com.tikitaka.triptroop.report.domain.entity.Report;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportRepository extends JpaRepository<Report, Long> {

    /* Repository 앞에 부분을 지우고 본인의 기능 폴더명을 적어주세요. */

}
