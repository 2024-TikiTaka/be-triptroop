package com.tikitaka.triptroop.travel.dto.response;

import com.tikitaka.triptroop.travel.domain.entity.Travel;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class TravelResponse { //<- 무슨 응답인지 알수 있게 Response 앞에 명시해주세요. ( ex) 프로필 응답 일때 -> ProfileResponse )

    /* 응답시 사용할 필드명을 입력해주세요. */

    private final Long id;
    private final String title;
    private final String content;

    public static TravelResponse from(final Travel travel) {
        return new TravelResponse(
                travel.getId(),
                travel.getTitle(),
                travel.getContent()
        );
    }
}
