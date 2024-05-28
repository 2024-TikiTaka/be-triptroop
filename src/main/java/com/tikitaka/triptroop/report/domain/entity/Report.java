package com.tikitaka.triptroop.report.domain.entity;

import com.tikitaka.triptroop._example.domain.entity.Companion;
import com.tikitaka.triptroop.report.domain.type.ReportProcessStatus;
import com.tikitaka.triptroop.report.domain.type.ReportTarget;
import com.tikitaka.triptroop.report.domain.type.ReportType;
import com.tikitaka.triptroop.schedule.domain.entity.Schedule;
import com.tikitaka.triptroop.travel.domain.entity.Travel;
import com.tikitaka.triptroop.user.domain.entity.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Entity
@Table(name = "reports")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @ManyToOne
//    @JoinColumn(name="reporter_id")
//    private User reporterId;
    private Long reporterId;

    @Enumerated(value = EnumType.STRING)
    private ReportTarget kind;

    @ManyToOne
    @JoinColumn(name="schedule_id")
    private Schedule scheduleId;

    @ManyToOne
    @JoinColumn(name="repertee_id")
    private User reporteeId;

    @ManyToOne
    @JoinColumn(name="travel_id")
    private Travel travelId;

    @ManyToOne
    @JoinColumn(name="companion_id")
    private Companion companionId;

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
