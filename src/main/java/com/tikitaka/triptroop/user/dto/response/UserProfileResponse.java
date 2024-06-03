package com.tikitaka.triptroop.user.dto.response;

import com.tikitaka.triptroop.user.domain.entity.Profile;
import com.tikitaka.triptroop.user.domain.entity.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.time.Period;

/**
 * 회원 프로필 정보
 * 나이 구간, 성별, 고도, 닉네임, 프로필이미지, 설명, mbti
 */
@Getter
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class UserProfileResponse {

    private final Long userId;

    private final String ageRange;

    private final String gender;

    private final int godo;

    private final ProfileResponse profile;

    public UserProfileResponse(Long userId, String ageRange, String gender, int godo, Profile profile) {
        this.userId = userId;
        this.ageRange = ageRange;
        this.gender = gender;
        this.godo = godo;
        this.profile = ProfileResponse.of(profile);

    }

    public static UserProfileResponse of(User user, Profile profile) {
        return new UserProfileResponse(
                user.getId(),
                calculateAgeRange(user.getBirth()),
                user.getGender().toString(),
                user.getGodo(),
                ProfileResponse.from(profile)
        );
    }

    public static UserProfileResponse from(User user, Profile profile) {
        return new UserProfileResponse(
                user.getId(),
                calculateAgeRange(user.getBirth()),
                user.getGender().toString(),
                user.getGodo(),
                ProfileResponse.from(profile)
        );
    }

    private static String calculateAgeRange(LocalDate birth) {
        int age = Period.between(birth, LocalDate.now()).getYears();
        return String.valueOf(Math.floor((age / 10)));
    }
}
