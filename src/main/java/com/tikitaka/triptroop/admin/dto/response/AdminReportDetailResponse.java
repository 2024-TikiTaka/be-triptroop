package com.tikitaka.triptroop.admin.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.tikitaka.triptroop.image.dto.response.ImageOriginalResponse;
import com.tikitaka.triptroop.image.util.ImageUtils;
import com.tikitaka.triptroop.report.domain.entity.Report;
import com.tikitaka.triptroop.report.domain.type.ReportKind;
import com.tikitaka.triptroop.report.domain.type.ReportProcessStatus;
import com.tikitaka.triptroop.report.domain.type.ReportType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@RequiredArgsConstructor
public class AdminReportDetailResponse {

    private final Long reportId;
    private final Long reporterId;
    private final String name;
    private final ReportKind kind;
    private final Long kindReportId;
    private final String targetName;
    private final ReportType type;
    private final String content;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private final LocalDateTime reportedAt;
    private final ReportProcessStatus status;
    private final List<String> imageNames;

    public static AdminReportDetailResponse from(
            final Report report, String name, Long kindReportId,
            String targetName, List<ImageOriginalResponse> imageOriginalResponses
    ) {
        List<String> imageNames = ImageUtils.extractImageInfo(imageOriginalResponses);
        return new AdminReportDetailResponse(
                report.getId(),
                report.getReporterId(),
                name,
                report.getKind(),
                kindReportId,
                targetName,
                report.getType(),
                report.getContent(),
                report.getReportedAt(),
                report.getStatus(),
                imageNames
        );
    }
}
