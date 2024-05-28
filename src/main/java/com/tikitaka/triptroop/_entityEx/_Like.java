package com.tikitaka.triptroop._entityEx;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "likes")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class _Like {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private _User user;

    @Column(nullable = false)
    private String kind;

    @ManyToOne
    @JoinColumn(name = "schedule_id")
    private _Schedule schedule;

    @ManyToOne
    @JoinColumn(name = "travel_id", nullable = false)
    private _Travel travel;

    @ManyToOne
    @JoinColumn(name = "companion_id")
    private _Companion companion;
}
