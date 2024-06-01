package com.tikitaka.triptroop.user.dto.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class UserSaveRequest {

    private final String phoneNumber;
}
