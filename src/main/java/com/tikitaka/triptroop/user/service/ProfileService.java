package com.tikitaka.triptroop.user.service;

import com.tikitaka.triptroop.common.exception.NotFoundException;
import com.tikitaka.triptroop.common.exception.type.ExceptionCode;
import com.tikitaka.triptroop.user.domain.entity.Profile;
import com.tikitaka.triptroop.user.domain.entity.User;
import com.tikitaka.triptroop.user.domain.repository.ProfileRepository;
import com.tikitaka.triptroop.user.domain.repository.UserRepository;
import com.tikitaka.triptroop.user.dto.response.UserProfileResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ProfileService {

    private final ProfileRepository profileRepository;

    private final UserRepository userRepository;

    /**
     * 회원 번호로 프로필 정보 조회
     *
     * @return UserProfileResponse (회원번호, 나이(범위), 성별, 고도, 닉네임, 프로필이미지, 자기소개, MBTI)
     */
    @Transactional(readOnly = true)
    public UserProfileResponse findUserProfileByUserId(Long userId) {

        final User user = userRepository.findById(userId)
                                        .orElseThrow(() -> new NotFoundException(ExceptionCode.NOT_FOUND_USER));

        final Profile profile = profileRepository.findByUserId(userId)
                                                 .orElseThrow(() -> new NotFoundException(ExceptionCode.NOT_FOUND_USER_PROFILE));

        return UserProfileResponse.of(user, profile);
    }
}
