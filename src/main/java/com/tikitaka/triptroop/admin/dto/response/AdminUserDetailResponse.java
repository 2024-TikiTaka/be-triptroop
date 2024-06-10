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
    private final Long reportCount;
    private final Provider provider;
    private final String password;
    private final Integer godo;
    private final Double reviewPoint;
    private final String introduction;
    private final List<String> interestNames;
    private final Long inquiriesCount;
    private final String mbti;
    private final Long contentCount;
    private final Long friendCount;
//    private final Long loginCount;     TODO : 다솔 - 관리자 > 회원관리 > 회원상세조회 - 회원 로그인 횟수 기록 하고 조회해오기
//    private final Long loginTimeTotal;  TODO : 다솔 - 관리자 > 회원관리 > 회원상세조회 - 회원 로그인 총 시간 기록 하고 조회해오기

    public AdminUserDetailResponse(Long userId, String email, String nickname, UserRole role,
                                   LocalDateTime createdAt, String name, Gender gender, UserStatus status,
                                   LocalDate birth, String phone, Long reportCount, Provider provider,
                                   String password, Integer godo, Double reviewPoint, String introduction,
                                   String interestNames, Long inquiriesCount, String mbti, Long contentCount,
                                   Long friendCount/*, Long loginCount, Long loginTimeTotal*/) {
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
//        this.loginCount = loginCount;
//        this.loginTimeTotal = loginTimeTotal;
    }
}
