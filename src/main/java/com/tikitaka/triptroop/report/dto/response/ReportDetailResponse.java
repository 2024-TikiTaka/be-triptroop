package com.tikitaka.triptroop.report.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.tikitaka.triptroop.image.dto.response.ImageResponse;
import com.tikitaka.triptroop.report.domain.entity.Report;
import com.tikitaka.triptroop.report.domain.type.ReportKind;
import com.tikitaka.triptroop.report.domain.type.ReportProcessStatus;
import com.tikitaka.triptroop.report.domain.type.ReportType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class ReportDetailResponse {

    private final Long id;
    private final ReportProcessStatus status;
    private final LocalDateTime processedAt;
    private final ReportKind kind;
    private final Long reporterId;
    private final Long scheduleId;
    private final Long reporteeId;
    private final Long travelId;
    private final Long companionId;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private final LocalDateTime reportedAt;
    private final ReportType type;
    private final String content;
    private final List<String> imageNames;
    private final List<String> imageExtensions;

    public static ReportDetailResponse from(final Report report, List<ImageResponse> imageResponses, Long scheduleId, Long reporteeId, Long travelId, Long companionId) {
        List<String> imageNames = imageResponses.stream()
                .map(imageResponse -> {
                    String fullPath = imageResponse.getFullPath();
                    String fileName = fullPath.substring(fullPath.lastIndexOf('/') + 1, fullPath.lastIndexOf('.'));
                    return fileName;
                })
                .collect(Collectors.toList());

        List<String> imageExtensions = imageResponses.stream()
                .map(imageResponse -> {
                    String fullPath = imageResponse.getFullPath();
                    String extension = fullPath.substring(fullPath.lastIndexOf('.') + 1);
                    return extension;
                })
                .collect(Collectors.toList());


        return new ReportDetailResponse(
                report.getId(),
                report.getStatus(),
                report.getProcessedAt(),
                report.getKind(),
                report.getReporterId(),
                scheduleId,
                reporteeId,
                travelId,
                companionId,
                report.getReportedAt(),
                report.getType(),
                report.getContent(),
                imageNames,
                imageExtensions
        );
    }
}
