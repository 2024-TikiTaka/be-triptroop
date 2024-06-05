package com.tikitaka.triptroop.admin.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.tikitaka.triptroop.review.domain.entity.UserReview;
import com.tikitaka.triptroop.user.domain.entity.Profile;
import com.tikitaka.triptroop.user.domain.entity.SocialUser;
import com.tikitaka.triptroop.user.domain.entity.User;
import com.tikitaka.triptroop.user.domain.type.Provider;
import com.tikitaka.triptroop.user.domain.type.UserRole;
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

    private final String name;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private final LocalDate birth;

    private final String phone;

    private final Integer reportCount;

    private final Provider provider;

    private final String password;

    private final Integer godo;

    private final Integer reviewPoint;

    private final String introduction;


    private AdminUserResponse(User user, SocialUser socialUser, UserReview userReview, Integer reportCount, Profile profile) {
        this.userId = user.getId();
        this.nickname = profile.getNickname();
        this.role = user.getRole();
        this.reportCount = getReportCount();
        this.phone = user.getPhone();
    }

    public static AdminUserResponse of(User user, SocialUser socialUser, UserReview userReview, Integer reportCount, Profile profile) {
        return new AdminUserResponse(
                user.getId(),
                profile.getNickname(),
                user.getRole(),
                reportCount,
                user.getPhone()
        );
    }

}




