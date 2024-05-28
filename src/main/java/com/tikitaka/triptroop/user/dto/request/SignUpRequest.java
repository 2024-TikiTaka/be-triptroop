package com.tikitaka.triptroop.user.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@RequiredArgsConstructor
@ToString
public class SignUpRequest {

    @NotBlank
    private final String email;

    @NotBlank
    @Size(min = 8, max = 20)
    private final String password;

    @NotBlank
    private final String name;

    @Past
    @JsonFormat(pattern = "YYYY-MM-DD")
    // @DateTimeFormat
    private final LocalDate birth;

    private final String gender;
}
