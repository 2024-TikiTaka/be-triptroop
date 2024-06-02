package com.tikitaka.triptroop.report.service;

import com.tikitaka.triptroop.common.exception.NotFoundException;
import com.tikitaka.triptroop.common.exception.type.ExceptionCode;
import com.tikitaka.triptroop.companion.domain.repository.CompanionRepository;
import com.tikitaka.triptroop.image.domain.repository.ImageRepository;
import com.tikitaka.triptroop.report.domain.entity.Report;
import com.tikitaka.triptroop.report.domain.repository.ReportRepository;
import com.tikitaka.triptroop.report.domain.type.ReportKind;
import com.tikitaka.triptroop.report.dto.response.ReportDetailResponse;
import com.tikitaka.triptroop.report.dto.response.ReportTableResponse;
import com.tikitaka.triptroop.schedule.domain.repository.ScheduleRepository;
import com.tikitaka.triptroop.travel.domain.repository.TravelRepository;
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
    private final ScheduleRepository scheduleRepository;
    private final TravelRepository travelRepository;
    private final CompanionRepository companionRepository;
    private final ImageRepository imageRepository;

    /* 1. 신고 목록 조회 Test */
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

    /* 2. 신고 상세 조회 Test */
    public ReportDetailResponse getReportDetail(Long reportId) {
        Report report = reportRepository.findById(reportId)
                .orElseThrow(() -> new NotFoundException(ExceptionCode.NOT_FOUND_REPORT));

        Long reporterId = report.getReporterId();
        Long scheduleId = null;
        Long reporteeId = null;
        Long travelId = null;
        Long companionId = null;

        ReportKind kind = report.getKind();
        switch (kind) {
            case SCHEDULE -> scheduleId = report.getScheduleId();
            case USER -> reporteeId = report.getReporteeId();
            case TRAVEL -> travelId = report.getTravelId();
            case COMPANION -> companionId = report.getCompanionId();
        }

//        Schedule schedule = scheduleRepository.findById(scheduleId);
//        Profile profile = profileRepository.findById(reporteeId);
//        Travel travel = travelRepository.findById(travelId);
//        Companion companion = companionRepository.findById(companionId);
//
//        List<Image> images = imageRepository.findByReportId(reportId);
//        List<ImageResponse> imageResponses = ImageResponse.from(images);
//
//        return ReportDetailResponse.from(report, imageResponses, schedule, profile, travel, companion);
        return null;
    }
}
