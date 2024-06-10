package com.tikitaka.triptroop.user.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@RequiredArgsConstructor
public class SignUpRequest {

    @NotBlank(message = "이메일은 필수 항목입니다.")
    private final String email;

    @NotBlank(message = "비밀번호는 필수 항목입니다.")
    @Size(min = 8, max = 20, message = "8-20자 이내로 입력해주세요.")
    private final String password;

    @NotBlank(message = "이름은 필수 항목입니다.")
    @Pattern(regexp = "^[가-힣]{2,}$", message = "이름의 형식이 올바르지 않습니다.")
    private final String name;

    @NotBlank(message = "생년월일은 필수 항목입니다.")
    @Pattern(regexp = "\\d{8}", message = "생년월일의 형식이 올바르지 않습니다. (ex. 19900101)")
    private final String birth;

    @NotBlank(message = "성별은 필수 항목입니다.")
    @Pattern(regexp = "M|F", message = "성별의 형식이 올바르지 않습니다. (ex. 여자-F/남자-M)")
    private final String gender;
}
