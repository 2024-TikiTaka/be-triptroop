package com.tikitaka.triptroop._entityEx;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "reports")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class _Report {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="reporter_id")
    private _User reporterId;

    private String kind;

    @ManyToOne
    @JoinColumn(name="schedule_id")
    private _Schedule scheduleId;

    @ManyToOne
    @JoinColumn(name="repertee_id")
    private _User reporteeId;

    @ManyToOne
    @JoinColumn(name="travel_id")
    private _Travel travelId;

    @ManyToOne
    @JoinColumn(name="companion_id")
    private _Companion companionId;

    private String type;

    @Column(columnDefinition = "TEXT")
    private String content;

    @Column(columnDefinition = "VARCHAR(10) DEFAULT 'REQUESTED'")
    private String status;

    @Column(columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime reportedAt;

    private LocalDateTime processedAt;

}
