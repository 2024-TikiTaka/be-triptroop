package com.tikitaka.triptroop.user.domain.entity;

import com.tikitaka.triptroop.user.domain.type.Gender;
import com.tikitaka.triptroop.user.domain.type.MatchStatus;
import com.tikitaka.triptroop.user.domain.type.UserRole;
import com.tikitaka.triptroop.user.domain.type.UserStatus;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String password;

    private String name;

    private LocalDate birth;

    private UserRole role;

    private Gender gender;

    private String phone;

    private int godo;

    private String sns_provider;

    private String refresh_token;

    private LocalDateTime expired_at;

    private MatchStatus isMatched;

    private UserStatus status;

    private LocalDateTime created_at;

    private LocalDateTime modified_at;

    private LocalDateTime deleted_at;
}
