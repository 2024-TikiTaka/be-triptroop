package com.tikitaka.triptroop.user.dto.response;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class UserResponse {

    private final Long userId;

    private final String age;

    private final String gender;

    private final int godo;

    // public static UserResponse of(User user) {
    //     return new UserResponse(
    //             user.getId(),
    //             getAgeRestriction(user.getBirth()),
    //             user.getGender().toString(),
    //             user.getGodo()
    //
    //     );
    // }
    //
    // private int getAgeRestriction(LocalDate birth) {
    //     int age = LocalDate.now().getYear() - birth.getYear();
    //     return (int) Math.floor((age / 10));
    //
    // }
}
