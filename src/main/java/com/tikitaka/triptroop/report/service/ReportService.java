package com.tikitaka.triptroop.report.service;

import com.tikitaka.triptroop.common.exception.NotFoundException;
import com.tikitaka.triptroop.common.exception.type.ExceptionCode;
import com.tikitaka.triptroop.companion.domain.entity.Companion;
import com.tikitaka.triptroop.companion.domain.repository.CompanionRepository;
import com.tikitaka.triptroop.image.domain.entity.Image;
import com.tikitaka.triptroop.image.domain.repository.ImageRepository;
import com.tikitaka.triptroop.image.domain.type.ImageKind;
import com.tikitaka.triptroop.image.dto.response.ImageOriginalResponse;
import com.tikitaka.triptroop.image.service.ImageService;
import com.tikitaka.triptroop.report.domain.entity.Report;
import com.tikitaka.triptroop.report.domain.repository.ReportRepository;
import com.tikitaka.triptroop.report.domain.type.ReportKind;
import com.tikitaka.triptroop.report.domain.type.ReportProcessStatus;
import com.tikitaka.triptroop.report.domain.type.ReportType;
import com.tikitaka.triptroop.report.dto.request.ReportRequest;
import com.tikitaka.triptroop.report.dto.response.ReportDetailResponse;
import com.tikitaka.triptroop.report.dto.response.ReportTableResponse;
import com.tikitaka.triptroop.schedule.domain.entity.Schedule;
import com.tikitaka.triptroop.schedule.domain.repository.ScheduleRepository;
import com.tikitaka.triptroop.travel.domain.entity.Travel;
import com.tikitaka.triptroop.travel.domain.repository.TravelRepository;
import com.tikitaka.triptroop.user.domain.entity.Profile;
import com.tikitaka.triptroop.user.domain.repository.ProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

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

    private final ImageService imageService;

    /* 1. 신고 목록 조회 Test */
    @Transactional(readOnly = true)
    public List<ReportTableResponse> getReport(final String nickname, String kind) {
        Profile profile = profileRepository.findByNickname(nickname)
                .orElseThrow(() -> new NotFoundException(ExceptionCode.NOT_FOUND_USER));

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

        Long scheduleId = null;
        Long reporteeId = null;
        Long travelId = null;
        Long companionId = null;
        String titleOrNickname = null;

        ReportKind kind = report.getKind();
        switch (kind) {
            case SCHEDULE -> {
                scheduleId = report.getScheduleId();
                Schedule schedule = scheduleRepository.findById(scheduleId)
                        .orElseThrow(() -> new NotFoundException(ExceptionCode.NOT_FOUND_SCHEDULE));
                titleOrNickname = schedule.getTitle();
            }
            case USER -> {
                reporteeId = report.getReporteeId();
                Profile profile = profileRepository.findByUserId(reporteeId)
                        .orElseThrow(() -> new NotFoundException(ExceptionCode.NOT_FOUND_USER_PROFILE));
                titleOrNickname = profile.getNickname();
            }
            case TRAVEL -> {
                travelId = report.getTravelId();
                Travel travel = travelRepository.findById(travelId)
                        .orElseThrow(() -> new NotFoundException(ExceptionCode.NOT_FOUND_TRAVEL));
                titleOrNickname = travel.getTitle();
            }
            case COMPANION -> {
                companionId = report.getCompanionId();
                Companion companion = companionRepository.findById(companionId)
                        .orElseThrow(() -> new NotFoundException(ExceptionCode.NOT_FOUND_COMPANION));
                titleOrNickname = companion.getTitle();
            }
        }

        List<Image> images = imageRepository.findByReportId(reportId);
        List<ImageOriginalResponse> imageOriginalResponses = ImageOriginalResponse.from(images);

        return ReportDetailResponse.from(report, imageOriginalResponses, titleOrNickname);
    }

    @Transactional
    public Long save(final ReportRequest reportRequest, final Long reporterId, List<MultipartFile> images) {

        final Report newReport = Report.of(
                reporterId,
                ReportKind.valueOf(reportRequest.getKind()),
                reportRequest.getScheduleId(),
                reportRequest.getReporteeId(),
                reportRequest.getTravelId(),
                reportRequest.getCompanionId(),
                ReportType.valueOf(reportRequest.getType()),
                reportRequest.getContent(),
                ReportProcessStatus.valueOf(reportRequest.getStatus())
        );

        final Report report = reportRepository.save(newReport);
        imageService.saveAll(ImageKind.REPORT, report.getId(), images);

        return report.getId();
    }
}
