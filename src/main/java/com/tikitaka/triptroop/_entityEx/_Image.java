package com.tikitaka.triptroop._entityEx;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "images")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class _Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "travel_id")
    private _Travel travel;

    @ManyToOne
    @JoinColumn(name = "schedule_id")
    private _Schedule schedule;

    @ManyToOne
    @JoinColumn(name = "companion_id")
    private _Companion companion;

    @ManyToOne
    @JoinColumn(name = "report_id")
    private _Report report;

    @ManyToOne
    @JoinColumn(name = "inquiry_id")
    private _Inquiry inquiry;

    @Column(nullable = false)
    private String kind;

    @Column(nullable = false)
    private String path;

    @Column(nullable = false)
    private String uuid;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String extension;

    @Column(nullable = false, columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdAt;
}
