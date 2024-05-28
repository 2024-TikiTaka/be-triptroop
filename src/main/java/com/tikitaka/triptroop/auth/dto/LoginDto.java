package com.tikitaka.triptroop.auth.dto;

import com.tikitaka.triptroop.user.domain.type.UserRole;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
// @RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class LoginDto {

    private String email;

    private String password;

    private UserRole role;
}
