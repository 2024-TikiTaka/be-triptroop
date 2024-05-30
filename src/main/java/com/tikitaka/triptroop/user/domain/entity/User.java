package com.tikitaka.triptroop.user.domain.entity;

import com.tikitaka.triptroop.common.domain.BaseTimeEntity;
import com.tikitaka.triptroop.user.domain.type.Gender;
import com.tikitaka.triptroop.user.domain.type.UserRole;
import com.tikitaka.triptroop.user.domain.type.UserStatus;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SQLDelete(sql = "UPDATE users SET status = 'SUSPENDED' WHERE email = ?")
public class User extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    private String email;

    private String password;

    private String name;

    private LocalDate birth;

    @Enumerated(value = EnumType.STRING)
    private Gender gender;

    private String phone;

    private Integer godo = 38;

    @Enumerated(value = EnumType.STRING)
    private UserStatus status = UserStatus.ACTIVE;

    private boolean isMatched = true;

    @Enumerated(value = EnumType.STRING)
    private UserRole role = UserRole.USER;

    private String refreshToken;

    private LocalDateTime expiredAt;

    private LocalDateTime deletedAt;

    private User(String email, String password, String name, LocalDate birth, Gender gender) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.birth = birth;
        this.gender = gender;
    }

    public static User of(String email, String password, String name, LocalDate birth, String gender) {
        return new User(
                email,
                password,
                name,
                birth,
                Gender.valueOf(gender)
        );
    }

    public void updateRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
