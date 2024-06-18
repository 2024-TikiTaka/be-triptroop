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
    private Long id;

    private Long userId;

    private String nickname;

    private String profileImage;

    private String introduction;

    private String mbti;

    /* 다솔 추가 - 수정 쪽 추가 */
    private Profile(Long userId, String introduction, String mbti) {
        this.userId = userId;
        this.introduction = introduction;
        this.mbti = mbti;
    }

    private Profile(Long userId, String nickname, String introduction, String mbti) {
        this.userId = userId;
        this.nickname = nickname;
        this.introduction = introduction;
        this.mbti = mbti;
    }

    private Profile(Long userId, String nickname, String profileImage, String introduction, String mbti) {
        this.userId = userId;
        this.nickname = nickname;
        this.profileImage = profileImage;
        this.introduction = introduction;
        this.mbti = mbti;
    }

    public static Profile of(Long userId, String nickname, String profileImage, String introduction, String mbti) {
        return new Profile(userId, nickname, profileImage, introduction, mbti);
    }

    public static Profile of(Long userId, String nickname, String introduction, String mbti) {
        return new Profile(userId, nickname, introduction, mbti);
    }

    public void updateProfile(String nickname, String introduction, String mbti) {
        this.nickname = nickname;
        this.introduction = introduction;
        this.mbti = mbti;
    }

    public void updateProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    public void deleteProfileImage() {
        this.profileImage = null;
    }
}
