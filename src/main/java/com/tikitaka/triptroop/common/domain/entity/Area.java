package com.tikitaka.triptroop.common.domain.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "areas")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Area {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String sido;
}
