package com.tikitaka.triptroop.image.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ImageCreateRequest { //<- 무슨 요청인지 알수 있게 Request 앞에 명시해주세요. ( ex) 회원가입 요청 일때 -> SignupRequest  )

    //요청시 사용할 필드명을 적어주세요.
//    private final Long travelId;
    @NotBlank
    private final String path;
    private final Long scheduleId;
    @NotBlank
    private final String uuid;
    @NotBlank
    private final String name;
    @NotBlank
    private final String extension;
}
