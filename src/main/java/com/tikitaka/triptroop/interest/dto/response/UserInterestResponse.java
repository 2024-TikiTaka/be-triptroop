package com.tikitaka.triptroop.interest.dto.response;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class UserInterestResponse {

    private final Long userInterestId;
    private final Long interestId;
    private final Long userId;

    public UserInterestResponse of(Long userInterestId, Long interestId, Long userId) {
        return new UserInterestResponse(userInterestId, interestId, userId);
    }
}
