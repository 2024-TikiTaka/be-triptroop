package com.tikitaka.triptroop.user.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class PasswordRequest {

    @NotBlank
    @Size(min = 8, max = 20)
    private final String currentPassword;

    @NotBlank
    @Size(min = 8, max = 20)
    private final String newPassword;
}
