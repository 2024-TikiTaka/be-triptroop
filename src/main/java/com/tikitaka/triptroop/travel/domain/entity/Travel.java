package com.tikitaka.triptroop.travel.domain.entity;

import com.tikitaka.triptroop.common.domain.entity.Area;
import com.tikitaka.triptroop.common.domain.entity.BaseTimeEntity;
import com.tikitaka.triptroop.common.domain.entity.Category;
import com.tikitaka.triptroop.common.domain.type.Visibility;
import com.tikitaka.triptroop.image.domain.entity.Image;
import com.tikitaka.triptroop.place.domain.entity.Place;
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

    private Long userId;

    private String title;

    private String content;

    @Enumerated(EnumType.STRING)
    private Visibility visibility = Visibility.PUBLIC;

    private Boolean isDeleted = false;

    private LocalDateTime deletedAt;
    private int views;


//    @ManyToOne
//    @JoinColumn(name = "profileId")
//    private Profile profile;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "area_id")
    private Area area;

    @ManyToOne
    @JoinColumn(name = "place_id")
    private Place place;

    @OneToMany
    @JoinColumn(name = "travelId")
    private List<Image> images;

    @OneToMany
    @JoinColumn(name = "travelId")
    private List<TravelComment> travelComments;


    private Travel(Long userId,
                   Category category,
                   Area area,
                   Place place,
                   String title,
                   String content) {
        this.userId = userId;
        this.category = category;
        this.area = area;
        this.place = place;
        this.title = title;
        this.content = content;
    }

    public static Travel of(
            final Long userId,
            final Category category,
            final Area area,
            final Place place,
            final String title,
            final String content
    ) {
        return new Travel(
                userId,
                category,
                area,
                place,
                title,
                content
        );
    }

    public void increaseViews() {
        this.views++;
    }
}
