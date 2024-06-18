package com.tikitaka.triptroop.report.dto.response;

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
public class ReportDetailResponse {

    private final Long id;
    private final ReportProcessStatus status;
    private final LocalDateTime processedAt;
    private final ReportKind kind;
    private final Long reporterId;
    private final String titleOrNickname;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private final LocalDateTime reportedAt;
    private final ReportType type;
    private final String content;
    private final List<String> imageNames;

    public static ReportDetailResponse from(final Report report, List<ImageOriginalResponse> imageOriginalResponses, String titleOrNickname) {
        List<String> imageNames = ImageUtils.extractImageInfo(imageOriginalResponses);

        return new ReportDetailResponse(
                report.getId(),
                report.getStatus(),
                report.getProcessedAt(),
                report.getKind(),
                report.getReporterId(),
                titleOrNickname,
                report.getReportedAt(),
                report.getType(),
                report.getContent(),
                imageNames
        );
    }
}
