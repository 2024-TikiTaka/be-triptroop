package com.tikitaka.triptroop._entityEx;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "travel_log_items")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class _TravelLogItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "travel_log_id", nullable = false)
    private _TravelLog travelLog;

    @ManyToOne
    @JoinColumn(name = "place_id", nullable = false)
    private _Place place;

    @Column(nullable = false)
    private Integer order;

    @Column(nullable = false)
    private Integer point;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private LocalDate travelDate;

    @Column(nullable = false, columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdAt;

    @Column(nullable = false, columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private LocalDateTime modifiedAt;

    private LocalDateTime deletedAt;
}
