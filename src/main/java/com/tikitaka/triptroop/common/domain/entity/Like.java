package com.tikitaka.triptroop.common.domain.entity;

import com.tikitaka.triptroop.common.domain.type.ContentKind;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "likes")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Like {

    @Id
    @Column(name = "like_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private ContentKind kind;
    private Long scheduleId;
    private Long travelId;
    private Long companionId;
}
