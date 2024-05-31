package com.tikitaka.triptroop.common.security.dto;

import com.tikitaka.triptroop.user.domain.entity.User;
import com.tikitaka.triptroop.user.domain.type.UserRole;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class LoginDto {

    private final Long userId;

    private final String email;

    private final String password;

    private final UserRole role;

    public static LoginDto from(User user) {
        return new LoginDto(
                user.getId(),
                user.getEmail(),
                user.getPassword(),
                user.getRole()
        );
    }
}
