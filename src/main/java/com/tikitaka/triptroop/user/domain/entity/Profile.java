package com.tikitaka.triptroop.user.domain.entity;


import com.tikitaka.triptroop.common.domain.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "profiles")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Profile extends BaseTimeEntity {

    @Id
    @Column(name = "profile_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long profileId;

//

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String nickname;

    private String profileImage;

    private String introduction;

    private String mbti;
}
