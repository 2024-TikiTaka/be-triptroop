package com.tikitaka.triptroop.admin.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.tikitaka.triptroop.user.domain.entity.Profile;
import com.tikitaka.triptroop.user.domain.entity.User;
import com.tikitaka.triptroop.user.domain.type.Gender;
import com.tikitaka.triptroop.user.domain.type.UserRole;
import com.tikitaka.triptroop.user.domain.type.UserStatus;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AdminUserResponse {

    private final Long userId;

    private final String email;

    private final String nickname;

    private final UserRole role;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private final LocalDateTime createdAt;

    private final Gender gender;

    private final UserStatus status;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private final LocalDate birth;

    private final String phone;

    private final Integer godo;

    public AdminUserResponse(User user, Profile profile) {
        this.userId = user.getId();
        this.email = user.getEmail();
        this.nickname = profile.getNickname();
        this.role = user.getRole();
        this.createdAt = user.getCreatedAt();
        this.gender = user.getGender();
        this.status = user.getStatus();
        this.birth = user.getBirth();
        this.phone = user.getPhone();
        this.godo = user.getGodo();
    }

}




