package com.tikitaka.triptroop.user.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.tikitaka.triptroop.user.domain.entity.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserResponse {

    private final Long userId;

    private final String email;

    private final String name;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private final LocalDate birth;

    private final String gender;

    private final String phone;

    private final Integer godo;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm", timezone = "Asia/Seoul")
    private final LocalDateTime createdAt;

    public static UserResponse from(User user) {
        return new UserResponse(
                user.getId(),
                user.getEmail(),
                user.getName(),
                user.getBirth(),
                user.getGender().toString(),
                user.getPhone(),
                user.getGodo(),
                user.getCreatedAt()
        );
    }
}

