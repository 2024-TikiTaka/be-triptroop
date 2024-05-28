package com.tikitaka.triptroop._entityEx;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "companions")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class _Companion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private _User user;

    @ManyToOne
    @JoinColumn(name = "schedule_id", nullable = false)
    private _Schedule schedule;

    @Column(nullable = false)
    private String ageRestriction;

    @Column(nullable = false)
    private String genderRestriction;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    @Column(nullable = false, columnDefinition = "INT DEFAULT 0")
    private Integer views;

    @Column(nullable = false, columnDefinition = "VARCHAR(10) DEFAULT 'OPEN'")
    private String status;

    @Column(nullable = false, columnDefinition = "TINYINT DEFAULT 0")
    private Boolean isDeleted;

    @Column(nullable = false, columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdAt;

    @Column(nullable = false, columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private LocalDateTime modifiedAt;

    private LocalDateTime deletedAt;

    @OneToMany(mappedBy = "companion")
    private List<_UserReview> userReviews;

    @OneToMany(mappedBy = "companion")
    private List<_Like> likes;

    @OneToMany(mappedBy = "companion")
    private List<_Report> reports;

    @OneToMany(mappedBy = "companion")
    private List<_Image> images;
}
