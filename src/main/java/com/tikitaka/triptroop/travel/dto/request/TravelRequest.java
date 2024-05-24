package com.tikitaka.triptroop.travel.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class TravelRequest { //<- 무슨 요청인지 알수 있게 Request 앞에 명시해주세요. ( ex) 회원가입 요청 일때 -> SignupRequest  )

    //요청시 사용할 필드명을 적어주세요.

    @Min(value = 1)
    private final Long userId;
    @Min(value = 1)
    private final Long categoryId;
    @Min(value = 1)
    private final Long areaId;
    @Min(value = 1)
    private final Long placeId;
    @NotBlank
    private final String title;
    @NotBlank
    private final String content;


}
