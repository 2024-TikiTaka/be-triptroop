package com.tikitaka.triptroop.common.page;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class PageResponse {

    private final Object data;

    private final PagingButtonInfo pageInfo;

    public static PageResponse of(Object data, PagingButtonInfo pagingButtonInfo) {
        return new PageResponse(data, pagingButtonInfo);
    }
}
