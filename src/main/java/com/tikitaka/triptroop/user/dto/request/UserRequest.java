package com.tikitaka.triptroop.user.dto.request;

import com.tikitaka.triptroop.user.domain.entity.User;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
public class UserRequest {

    private final Long id;

    private final String email;

    private final String name;

    private final LocalDateTime createdAt;


    public static UserRequest from(User user) {
        return new UserRequest(
                user.getId(),
                user.getEmail(),
                user.getName(),
                user.getCreatedAt()
        );
    }
}
