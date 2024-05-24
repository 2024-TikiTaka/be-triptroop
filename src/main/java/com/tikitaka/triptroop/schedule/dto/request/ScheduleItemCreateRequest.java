package com.tikitaka.triptroop.schedule.dto.request;

import com.tikitaka.triptroop.schedule.domain.type.ScheduleItemKind;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@Getter
@RequiredArgsConstructor
public class ScheduleItemCreateRequest { //<- 무슨 요청인지 알수 있게 Request 앞에 명시해주세요. ( ex) 회원가입 요청 일때 -> SignupRequest  )

    //요청시 사용할 필드명을 적어주세요.

    @NotBlank
    private final String content;
    @Min(value = 1)
    private final int cost;
    @NotNull
    private final Long placeId;
    @NotNull
    private final LocalDate planDate;
    @NotNull
    private final ScheduleItemKind kind;
}
