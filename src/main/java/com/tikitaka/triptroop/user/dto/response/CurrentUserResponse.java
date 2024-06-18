package com.tikitaka.triptroop.user.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.tikitaka.triptroop.user.domain.entity.Profile;
import com.tikitaka.triptroop.user.domain.entity.User;
import com.tikitaka.triptroop.user.domain.type.Gender;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

/**
 * 회원 프로필 정보
 */
@Getter
@RequiredArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CurrentUserResponse {

    private final Long userId;

    private final String email;

    private final String name;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private final LocalDate birth;

    private final Gender gender;

    private final String phone;

    private final Integer godo;

    private final ProfileResponse profile;

    public CurrentUserResponse(User user, Profile profile) {
        this.userId = user.getId();
        this.email = user.getEmail();
        this.name = user.getName();
        this.birth = user.getBirth();
        this.gender = user.getGender();
        this.phone = user.getPhone();
        this.godo = user.getGodo();
        this.profile = ProfileResponse.from(profile);
    }

    public static CurrentUserResponse from(User user, Profile profile) {
        return new CurrentUserResponse(
                user.getId(),
                user.getEmail(),
                user.getName(),
                user.getBirth(),
                user.getGender(),
                user.getPhone(),
                user.getGodo(),
                ProfileResponse.from(profile)
        );
    }
}
