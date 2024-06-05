package com.tikitaka.triptroop.report.domain.entity;

import com.tikitaka.triptroop.image.domain.entity.Image;
import com.tikitaka.triptroop.report.domain.type.ReportKind;
import com.tikitaka.triptroop.report.domain.type.ReportProcessStatus;
import com.tikitaka.triptroop.report.domain.type.ReportType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "reports")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "report_id")
    private Long id;

    private Long reporterId;

    @Enumerated(value = EnumType.STRING)
    private ReportKind kind;

    private Long scheduleId;

    private Long reporteeId;

    private Long travelId;

    private Long companionId;

    @Enumerated(value = EnumType.STRING)
    private ReportType type;

    @Column(columnDefinition = "TEXT")
    private String content;

    @Enumerated(value = EnumType.STRING)
    private ReportProcessStatus status;

    @CreatedDate
    private LocalDateTime reportedAt;

    private LocalDateTime processedAt;

    @OneToMany
    @JoinColumn(name = "reportId")
    private List<Image> images;


    private Report(
            Long reporterId,
            ReportKind kind,
            Long scheduleId,
            Long reporteeId,
            Long travelId,
            Long companionId,
            ReportType type,
            String content,
            ReportProcessStatus status
    ) {
        this.reporterId = reporterId;
        this.kind = kind;
        this.scheduleId = scheduleId;
        this.reporteeId = reporteeId;
        this.travelId = travelId;
        this.companionId = companionId;
        this.type = type;
        this.content = content;
        this.status = status;
    }

    public static Report of(
            Long reporterId,
            ReportKind kind,
            Long scheduleId,
            Long reporteeId,
            Long travelId,
            Long companionId,
            ReportType type,
            String content,
            ReportProcessStatus status
    ) {
        return new Report(
                reporterId,
                kind,
                scheduleId,
                reporteeId,
                travelId,
                companionId,
                type,
                content,
                status
        );
    }


}
