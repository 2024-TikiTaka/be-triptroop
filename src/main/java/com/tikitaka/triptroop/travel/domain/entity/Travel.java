package com.tikitaka.triptroop.travel.domain.entity;

import com.tikitaka.triptroop.common.domain.entity.Area;
import com.tikitaka.triptroop.common.domain.entity.BaseTimeEntity;
import com.tikitaka.triptroop.common.domain.entity.Category;
import com.tikitaka.triptroop.common.domain.entity.Place;
import com.tikitaka.triptroop.common.domain.type.Visibility;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name = "travels")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class Travel extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    @ManyToOne
    @JoinColumn(name = "categoryId")
    private Category category;
    @ManyToOne
    @JoinColumn(name = "areaId")
    private Area area;
    @ManyToOne
    @JoinColumn(name = "placeId")
    private Place place;
    private String title;
    private String content;
    @Enumerated(EnumType.STRING)
    private Visibility visibility = Visibility.PUBLIC;
    private Boolean isDeleted = false;

    private LocalDateTime deletedAt;

}
