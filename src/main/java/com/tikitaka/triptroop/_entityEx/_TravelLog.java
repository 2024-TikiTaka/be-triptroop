package com.tikitaka.triptroop._entityEx;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "travel_logs")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class _TravelLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private _User user;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private _Category category;

    @ManyToOne
    @JoinColumn(name = "area_id", nullable = false)
    private _Area area;

    @Column(nullable = false)
    private LocalDate startDate;

    @Column(nullable = false)
    private LocalDate endDate;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, columnDefinition = "VARCHAR(10) DEFAULT 'PRIVATE'")
    private String visibility;

    @Column(nullable = false, columnDefinition = "TINYINT DEFAULT 0")
    private Boolean isDeleted;

    @Column(nullable = false, columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdAt;

    @Column(nullable = false, columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private LocalDateTime modifiedAt;

    private LocalDateTime deletedAt;

    @OneToMany(mappedBy = "travelLog")
    private List<_TravelLogItem> travelLogItems;
}
