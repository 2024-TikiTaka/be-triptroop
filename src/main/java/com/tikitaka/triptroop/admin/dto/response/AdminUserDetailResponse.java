package com.tikitaka.triptroop.admin.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.tikitaka.triptroop.user.domain.type.Gender;
import com.tikitaka.triptroop.user.domain.type.Provider;
import com.tikitaka.triptroop.user.domain.type.UserRole;
import com.tikitaka.triptroop.user.domain.type.UserStatus;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AdminUserDetailResponse {

    private final Long userId;
    private final String email;
    private final String nickname;
    private final UserRole role;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private final LocalDateTime createdAt;
    private final String name;
    private final Gender gender;
    private final UserStatus status;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private final LocalDate birth;
    private final String phone;
    private final Integer reportCount;
    private final Provider provider;
    private final String password;
    private final Integer godo;
    private final Integer reviewPoint;
    private final String introduction;
    private final List<String> interestNames;
    private final Integer inquiriesCount;
    private final String mbti;
    private final Integer contentCount;
    private final Integer friendCount;

    public AdminUserDetailResponse(Long userId, String email, String nickname, UserRole role,
                                   LocalDateTime createdAt, String name, Gender gender, UserStatus status,
                                   LocalDate birth, String phone, Integer reportCount, Provider provider,
                                   String password, Integer godo, Integer reviewPoint, String introduction,
                                   String interestNames, Integer inquiriesCount, String mbti, Integer contentCount,
                                   Integer friendCount) {
        this.userId = userId;
        this.email = email;
        this.nickname = nickname;
        this.role = role;
        this.createdAt = createdAt;
        this.name = name;
        this.gender = gender;
        this.status = status;
        this.birth = birth;
        this.phone = phone;
        this.reportCount = reportCount;
        this.provider = provider;
        this.password = password;
        this.godo = godo;
        this.reviewPoint = reviewPoint;
        this.introduction = introduction;
        this.interestNames = interestNames != null ? List.of(interestNames.split(",")) : null;
        this.inquiriesCount = inquiriesCount;
        this.mbti = mbti;
        this.contentCount = contentCount;
        this.friendCount = friendCount;
    }
}
