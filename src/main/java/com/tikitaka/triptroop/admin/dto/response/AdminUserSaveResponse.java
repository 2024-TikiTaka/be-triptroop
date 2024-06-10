package com.tikitaka.triptroop.admin.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.tikitaka.triptroop.user.domain.entity.Profile;
import com.tikitaka.triptroop.user.domain.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
public class AdminUserSaveResponse {

    private final User user;
    private final Profile profile;

}
