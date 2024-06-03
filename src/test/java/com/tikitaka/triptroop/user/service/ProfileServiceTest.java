package com.tikitaka.triptroop.user.service;

import com.tikitaka.triptroop.user.dto.response.UserProfileResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ProfileServiceTest {

    @Autowired
    private ProfileService profileService;

    @Test
    @DisplayName("회원 프로필 조회_성공")
    void 회원_정보와_프로필_조회_성공() {

        UserProfileResponse userProfile = profileService.findUserProfileByUserId(1L);
        Assertions.assertNotNull(userProfile.getProfile());
    }
}