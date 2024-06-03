package com.tikitaka.triptroop.user.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@RequiredArgsConstructor
public class SignUpRequest {

    @NotBlank
    private final String email;

    @NotBlank
    @Size(min = 8, max = 20)
    private final String password;

    @NotBlank
    private final String name;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private final LocalDate birth;

    private final String gender;
}
