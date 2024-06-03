package com.tikitaka.triptroop.report.domain.entity;

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

    //    @ManyToOne
//    @JoinColumn(name = "reporter_id")
//    private User reporter;
    private Long reporterId;

    @Enumerated(value = EnumType.STRING)
    private ReportKind kind;

    //    @ManyToOne
//    @JoinColumn(name = "schedule_id")
//    private Schedule schedule;
    private Long scheduleId;

    //    @ManyToOne
//    @JoinColumn(name = "reportee_id")
//    private User reportee;
    private Long reporteeId;

    //    @ManyToOne
//    @JoinColumn(name = "travel_id")
//    private Travel travel;
    private Long travelId;

    //    @ManyToOne
//    @JoinColumn(name = "companion_id")
//    private Companion companion;
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

}
