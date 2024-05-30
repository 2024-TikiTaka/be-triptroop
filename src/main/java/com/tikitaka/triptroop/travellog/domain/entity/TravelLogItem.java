package com.tikitaka.triptroop.travellog.domain.entity;

import com.tikitaka.triptroop.common.domain.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "travel_log_items")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TravelLogItem extends BaseTimeEntity {

    @Id
    @Column(name = "travel_log_item_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long travelLogId;

    private Long placeId;

    private int order;

    private int point;

    private String content;

    private LocalDateTime deletedAt;
}
