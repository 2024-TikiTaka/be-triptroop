package com.tikitaka.triptroop.user.dto.response;

import com.tikitaka.triptroop.user.domain.entity.User;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 회원 정보 조회
 */
@Getter
@RequiredArgsConstructor
public class UserDetailResponse {

    private final Long id;

    private final String email;

    private final String name;

    private LocalDate birth;

    private String gender;

    private String phone;

    private Integer godo = 38;

    private final LocalDateTime createdAt;

    public static UserDetailResponse of(User user) {
        return new UserDetailResponse(
                user.getId(),
                user.getEmail(),
                user.getName(),
                user.getCreatedAt()
        );
    }
}
