package com.tikitaka.triptroop.admin.domain.repository;

import com.tikitaka.triptroop.report.domain.entity.Report;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AdminReportRepository extends JpaRepository<Report, Long> {

}

