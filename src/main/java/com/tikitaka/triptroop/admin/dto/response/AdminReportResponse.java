package com.tikitaka.triptroop.admin.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.tikitaka.triptroop.report.domain.entity.Report;
import com.tikitaka.triptroop.report.domain.type.ReportKind;
import com.tikitaka.triptroop.report.domain.type.ReportProcessStatus;
import com.tikitaka.triptroop.report.domain.type.ReportType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@RequiredArgsConstructor
public class AdminReportResponse {

    private final Long reportId;
    private final Long reporterId;
    private final String name;
    private final ReportKind kind;
    private final Long kindReportId;
    private final String targetName;
    private final ReportType type;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private final LocalDateTime reportedAt;
    private final ReportProcessStatus status;


    public static AdminReportResponse from(final Report report, final String name, final Long kindReportId, final String targetName) {
        return new AdminReportResponse(
                report.getId(),
                report.getReporterId(),
                name,
                report.getKind(),
                kindReportId,
                targetName,
                report.getType(),
                report.getReportedAt(),
                report.getStatus()
        );
    }

    public static List<AdminReportResponse> from(final List<Report> reports, final String name, final Long kindReportId, final String targetName) {
        List<AdminReportResponse> reportList = new ArrayList<>();

        for (Report report : reports) {
            reportList.add(new AdminReportResponse(
                    report.getId(),
                    report.getReporterId(),
                    name,
                    report.getKind(),
                    kindReportId,
                    targetName,
                    report.getType(),
                    report.getReportedAt(),
                    report.getStatus()
            ));
        }
        return reportList;
    }


}
