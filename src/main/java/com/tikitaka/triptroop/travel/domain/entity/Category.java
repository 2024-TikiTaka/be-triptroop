package com.tikitaka.triptroop.travel.domain.entity;

import com.tikitaka.triptroop.common.domain.entity.BaseTimeEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "categories")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Category extends BaseTimeEntity {

    @Id
    private Long id;
    private String name;
    private LocalDateTime deletedAt;
}
