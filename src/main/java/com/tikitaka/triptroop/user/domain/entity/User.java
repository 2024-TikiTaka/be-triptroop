package com.tikitaka.triptroop.user.domain.entity;

import com.tikitaka.triptroop.common.domain.BaseTimeEntity;
import com.tikitaka.triptroop.user.domain.type.Gender;
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
public class User extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String password;

    private String name;

    private LocalDate birth;

    @Enumerated(value = EnumType.STRING)
    private UserRole role = UserRole.USER;

    private Gender gender;

    private String phone;

    private int godo;

    @Enumerated(value = EnumType.STRING)
    private UserStatus status = UserStatus.ACTIVE;

    private boolean isMatched = true;

    private LocalDateTime deletedAt;
}
