package com.tikitaka.triptroop.admin.service;

import com.tikitaka.triptroop.admin.domain.repository.AdminReportRepository;
import com.tikitaka.triptroop.admin.dto.response.AdminReportResponse;
import com.tikitaka.triptroop.common.exception.NotFoundException;
import com.tikitaka.triptroop.common.exception.type.ExceptionCode;
import com.tikitaka.triptroop.companion.domain.entity.Companion;
import com.tikitaka.triptroop.companion.domain.repository.CompanionRepository;
import com.tikitaka.triptroop.report.domain.entity.Report;
import com.tikitaka.triptroop.schedule.domain.entity.Schedule;
import com.tikitaka.triptroop.schedule.domain.repository.ScheduleRepository;
import com.tikitaka.triptroop.travel.domain.entity.Travel;
import com.tikitaka.triptroop.travel.domain.repository.TravelRepository;
import com.tikitaka.triptroop.user.domain.entity.Profile;
import com.tikitaka.triptroop.user.domain.repository.ProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class AdminReportService {

    private final AdminReportRepository adminReportRepository;
    private final ProfileRepository profileRepository;
    private final ScheduleRepository scheduleRepository;
    private final CompanionRepository companionRepository;
    private final TravelRepository travelRepository;


    /* 1. 신고 관리 > 신고 목록 조회 */
    @Transactional(readOnly = true)
    public List<AdminReportResponse> getReportList() {
        List<Report> reportList = adminReportRepository.findAll();
        List<AdminReportResponse> responseList = new ArrayList<>();

        String name = null;
        Long kindReportId = null;
        String targetName = null;

        for (Report report : reportList) {
            Long reporterId = report.getReporterId();
            name = profileRepository.findByUserId(reporterId)
                    .map(Profile::getNickname).orElse("");
            String kind = report.getKind().name();
            switch (kind) {
                case "SCHEDULE" -> {
                    kindReportId = report.getScheduleId();
                    Schedule title = scheduleRepository.findById(kindReportId)
                            .orElseThrow(() -> new NotFoundException(ExceptionCode.NOT_FOUND_SCHEDULE));
                    targetName = title.getTitle();
                }
                case "USER" -> {
                    kindReportId = report.getReporteeId();
                    Profile nickname = profileRepository.findByUserId(kindReportId)
                            .orElseThrow(() -> new NotFoundException(ExceptionCode.NOT_FOUND_USER_PROFILE));
                    targetName = nickname.getNickname();
                }
                case "TRAVEL" -> {
                    kindReportId = report.getTravelId();
                    Travel title = travelRepository.findById(kindReportId)
                            .orElseThrow(() -> new NotFoundException(ExceptionCode.NOT_FOUND_TRAVEL));
                    targetName = title.getTitle();
                }
                case "COMPANION" -> {
                    kindReportId = report.getCompanionId();
                    Companion title = companionRepository.findById(kindReportId)
                            .orElseThrow(() -> new NotFoundException(ExceptionCode.NOT_FOUND_COMPANION));
                    targetName = title.getTitle();
                }
            }
            AdminReportResponse response = AdminReportResponse.from(report, name, kindReportId, targetName);
            responseList.add(response);

        }

        return responseList;

    }
}
