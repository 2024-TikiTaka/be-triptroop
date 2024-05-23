package com.tikitaka.triptroop.schedule.dto.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ScheduleCreateRequest { //<- 무슨 요청인지 알수 있게 Request 앞에 명시해주세요. ( ex) 회원가입 요청 일때 -> SignupRequest  )

    //요청시 사용할 필드명을 적어주세요.

    private final String title;
    private final Long count;
    private final Long areaId;
    private final Long userId;

}
