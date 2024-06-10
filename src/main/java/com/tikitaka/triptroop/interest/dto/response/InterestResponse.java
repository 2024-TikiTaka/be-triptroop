package com.tikitaka.triptroop.interest.dto.response;


import com.tikitaka.triptroop.interest.domain.entity.Interest;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@RequiredArgsConstructor
public class InterestResponse {

    private final Long interestId;
    private final String name;

    public static InterestResponse from(Interest interest) {
        return new InterestResponse(interest.getId(), interest.getName());
    }

    public static List<InterestResponse> from(List<Interest> interests) {
        return interests.stream()
                .map(InterestResponse::from)
                .collect(Collectors.toList());
    }

}
