package com.tikitaka.triptroop._entityEx;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "areas")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class _Area {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String sido;

    @OneToMany(mappedBy = "area")
    private List<_Travel> travels;

    @OneToMany(mappedBy = "area")
    private List<_TravelLog> travelLogs;

    @OneToMany(mappedBy = "area")
    private List<_Schedule> schedules;
}
