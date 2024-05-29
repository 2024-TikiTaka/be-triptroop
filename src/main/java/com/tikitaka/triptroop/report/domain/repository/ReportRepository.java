package com.tikitaka.triptroop.report.domain.repository;

import com.tikitaka.triptroop.report.domain.entity.Report;
import com.tikitaka.triptroop.report.domain.type.ReportTarget;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ReportRepository extends JpaRepository<Report, Long> {


    List<Report> findByReporterIdAndStatus(Long reporterId, ReportTarget status);
}
