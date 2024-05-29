package com.tikitaka.triptroop.travel.domain.entity;

import com.tikitaka.triptroop.common.domain.BaseTimeEntity;
import com.tikitaka.triptroop.common.domain.type.Visibility;
import com.tikitaka.triptroop.image.domain.entity.Image;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "travels")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Travel extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "travel_id")
    private Long id;

    private Long categoryId;

    private Long areaId;

    private Long placeId;

    private Long userId;

    private String title;

    private String content;

    private int views;

    @Enumerated(EnumType.STRING)
    private Visibility visibility = Visibility.PUBLIC;

    private Boolean isDeleted = false;

    private LocalDateTime deletedAt;

    @OneToMany
    @JoinColumn(name = "travelId")
    private List<Image> images;


    private Travel(Long userId,
                   Long categoryId,
                   Long areaId,
                   Long placeId,
                   String title,
                   String content) {
        this.userId = userId;
        this.categoryId = categoryId;
        this.areaId = areaId;
        this.placeId = placeId;
        this.title = title;
        this.content = content;
    }

    public static Travel of(
            final Long userId,
            final Long categoryId,
            final Long areaId,
            final Long placeId,
            final String title,
            final String content
    ) {
        return new Travel(
                userId,
                categoryId,
                areaId,
                placeId,
                title,
                content
        );
    }

    public void increaseViews() {
        this.views++;
    }
}
