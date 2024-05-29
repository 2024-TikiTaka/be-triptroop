package com.tikitaka.triptroop.report.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.tikitaka.triptroop.report.domain.entity.Report;
import com.tikitaka.triptroop.report.domain.type.ReportProcessStatus;
import com.tikitaka.triptroop.report.domain.type.ReportTarget;
import com.tikitaka.triptroop.report.domain.type.ReportType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class ReportTableResponse {

    private final Long id;
    private final ReportTarget kind;
    private final ReportType type;
    private final String content;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private final LocalDateTime reportedAt;
    private final ReportProcessStatus status;

    public static ReportTableResponse from(final Report report) {
        return new ReportTableResponse(
                report.getId(),
                report.getKind(),
                report.getType(),
                report.getContent(),
                report.getReportedAt(),
                report.getStatus()
        );
    }

}
