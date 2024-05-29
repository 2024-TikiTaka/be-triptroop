package com.tikitaka.triptroop.user.domain.entity;

import com.tikitaka.triptroop.common.domain.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "user_refresh_tokens")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserRefreshToken extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_refresh_token_id")
    private Long id;

    private Long userId;

    private String refreshToken;

    private LocalDateTime expiredAt;
}
