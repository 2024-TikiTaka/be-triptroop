package com.tikitaka.triptroop._entityEx;

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
public class _Travel {

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

    @ManyToOne
    @JoinColumn(name = "place_id", nullable = false)
    private _Place place;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false, columnDefinition = "VARCHAR(10) DEFAULT 'PUBLIC'")
    private String visibility;

    @Column(nullable = false, columnDefinition = "TINYINT DEFAULT 0")
    private Boolean isDeleted;

    @Column(nullable = false, columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdAt;

    @Column(nullable = false, columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private LocalDateTime modifiedAt;

    private LocalDateTime deletedAt;

    @OneToMany(mappedBy = "travel")
    private List<_TravelComment> travelComments;

    @OneToMany(mappedBy = "travel")
    private List<_Image> images;

    @OneToMany(mappedBy = "travel")
    private List<_Like> likes;

    @OneToMany(mappedBy = "travel")
    private List<_Report> reports;
}
