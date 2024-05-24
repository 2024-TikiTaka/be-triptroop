package com.tikitaka.triptroop.schedule.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@Getter
@RequiredArgsConstructor
public class ScheduleCreateRequest { //<- 무슨 요청인지 알수 있게 Request 앞에 명시해주세요. ( ex) 회원가입 요청 일때 -> SignupRequest  )

    //요청시 사용할 필드명을 적어주세요.

    @NotBlank
    private final String title;
    @Min(value = 1)
    private final Long count;
    @NotNull
    private final Long areaId;
    @NotNull
    private final Long userId;
    @NotNull
    private final LocalDate startDate;
    @NotNull
    private final LocalDate endDate;
}
