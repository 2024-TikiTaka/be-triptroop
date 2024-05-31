package com.tikitaka.triptroop.report.service;

import com.tikitaka.triptroop.common.exception.NotFoundException;
import com.tikitaka.triptroop.common.exception.type.ExceptionCode;
import com.tikitaka.triptroop.report.domain.entity.Report;
import com.tikitaka.triptroop.report.domain.repository.ReportRepository;
import com.tikitaka.triptroop.report.domain.type.ReportKind;
import com.tikitaka.triptroop.report.dto.response.ReportTableResponse;
import com.tikitaka.triptroop.user.domain.entity.Profile;
import com.tikitaka.triptroop.user.domain.repository.ProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class ReportService {

    private final ReportRepository reportRepository;
    private final ProfileRepository profileRepository;

    @Transactional(readOnly = true)
    public List<ReportTableResponse> getReport(final String nickname, String kind) {
        Profile profile = profileRepository.findByNickname(nickname);
        if (profile == null) {
            throw new NotFoundException(ExceptionCode.NOT_FOUND_USER);
        }

        Long reporterId = profile.getUserId();
        ReportKind reportKind = ReportKind.valueOf(kind.toUpperCase());
        List<Report> reports = reportRepository.findByReporterIdAndKind(reporterId, reportKind);
        if (reports.isEmpty()) {
            throw new NotFoundException(ExceptionCode.NOT_FOUND_REPORT);
        }

        return reports.stream().map(ReportTableResponse::from).collect(Collectors.toList());
    }

}
