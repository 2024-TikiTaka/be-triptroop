package com.tikitaka.triptroop.user.domain.entity;

import com.tikitaka.triptroop.user.domain.type.Provider;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name = "social_users")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class SocialUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "id")
    private User user;

    @Enumerated(value = EnumType.STRING)
    private Provider provider;

    private String accessToken;

    private String refreshToken;

    private LocalDateTime expiredAt;

}
