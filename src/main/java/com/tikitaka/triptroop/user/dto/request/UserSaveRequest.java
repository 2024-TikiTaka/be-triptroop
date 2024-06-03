package com.tikitaka.triptroop.user.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@RequiredArgsConstructor
public class UserSaveRequest {

    @NotBlank
    private final String name;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private final LocalDate birth;

    private final String gender;

    @NotBlank
    @Pattern(regexp = "^01(?:0|1|[6-9])[.-]?(\\d{3}|\\d{4})[.-]?(\\d{4})$", message = "올바른 전화번호 형식을 입력해주세요.")
    private final String phone;
}
