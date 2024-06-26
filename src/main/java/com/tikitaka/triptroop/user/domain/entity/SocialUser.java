package com.tikitaka.triptroop.user.domain.entity;

import com.tikitaka.triptroop.user.domain.type.Provider;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "social_users")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SocialUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "social_user_id")
    private Long id;

    private Long userId;

    @Enumerated(value = EnumType.STRING)
    private Provider provider;

    private String accessToken;

    private String refreshToken;

    private LocalDateTime expiredAt;
}
