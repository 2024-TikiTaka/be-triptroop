package com.tikitaka.triptroop._entityEx;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class _User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    private String password;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private LocalDate birth;

    @Column(nullable = false, columnDefinition = "VARCHAR(10) DEFAULT 'USER'")
    private String role;

    private Character gender;

    private String phone;

    @Column(nullable = false, columnDefinition = "INT DEFAULT 38")
    private Integer godo;

    @Column(nullable = false, columnDefinition = "VARCHAR(10) DEFAULT 'ACTIVE'")
    private String status;

    @Column(nullable = false, columnDefinition = "TINYINT DEFAULT 1")
    private Boolean isMatched;

    @Column(nullable = false, columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdAt;

    @Column(nullable = false, columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private LocalDateTime modifiedAt;

    private LocalDateTime deletedAt;

    @OneToMany(mappedBy = "user")
    private List<_Travel> travels;

    @OneToMany(mappedBy = "user")
    private List<_TravelLog> travelLogs;

    @OneToMany(mappedBy = "user")
    private List<_Schedule> schedules;

    @OneToMany(mappedBy = "user")
    private List<_Notification> notifications;

    @OneToMany(mappedBy = "user")
    private List<_Inquiry> inquiries;

    @OneToMany(mappedBy = "user")
    private List<_Profile> profiles;

    @OneToMany(mappedBy = "user")
    private List<_Friend> friends;

    @OneToMany(mappedBy = "user")
    private List<_Block> blocks;

    @OneToMany(mappedBy = "user")
    private List<_Like> likes;

    @OneToMany(mappedBy = "user")
    private List<_Report> reports;
}
