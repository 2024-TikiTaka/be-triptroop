package com.tikitaka.triptroop.user.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.tikitaka.triptroop.user.domain.entity.Profile;
import com.tikitaka.triptroop.user.domain.entity.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.time.Period;

/**
 * 회원 프로필 정보
 * 회원번호, 나이(범위), 성별, 고도, 닉네임, 프로필이미지, 자기소개, mbti
 */
@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserProfileResponse {

    private final Long userId;

    private final String ageRange;

    private final String gender;

    private final int godo;

    private final ProfileResponse profile;

    public static UserProfileResponse of(User user, Profile profile) {
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
        int ageRange = (age / 10) * 10;
        return String.valueOf(ageRange < 10 ? null : ageRange);
    }
}
