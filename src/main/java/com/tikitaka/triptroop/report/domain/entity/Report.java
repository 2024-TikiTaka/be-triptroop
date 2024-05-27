package com.tikitaka.triptroop.report.domain.entity;

import com.tikitaka.triptroop.schedule.domain.entity.Schedule;
import com.tikitaka.triptroop.travel.domain.entity.Travel;
import com.tikitaka.triptroop.user.domain.entity.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "reports")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="reporter_id")
    private User reporterId;

    private String kind;

    @ManyToOne
    @JoinColumn(name="schedule_id")
    private Schedule scheduleId;

    @ManyToOne
    @JoinColumn(name="repertee_id")
    private User reporteeId;

    @ManyToOne
    @JoinColumn(name="travel_id")
    private Travel travelId;

//    @ManyToOne  아직 해당 엔티티 없음
//    @JoinColumn(name="companion_id")
//    private Companion companionId;

    private String type;

    @Column(columnDefinition = "TEXT")
    private String content;

    @Column(columnDefinition = "VARCHAR(10) DEFAULT 'REQUESTED'")
    private String status;

    @Column(columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime reportedAt;

    private LocalDateTime processedAt;

}
