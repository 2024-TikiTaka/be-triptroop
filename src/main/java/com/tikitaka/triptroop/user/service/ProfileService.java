package com.tikitaka.triptroop.user.service;

import com.tikitaka.triptroop.common.exception.NotFoundException;
import com.tikitaka.triptroop.common.exception.type.ExceptionCode;
import com.tikitaka.triptroop.user.domain.entity.Profile;
import com.tikitaka.triptroop.user.domain.entity.User;
import com.tikitaka.triptroop.user.domain.repository.ProfileRepository;
import com.tikitaka.triptroop.user.domain.repository.UserRepository;
import com.tikitaka.triptroop.user.dto.request.ProfileSaveRequest;
import com.tikitaka.triptroop.user.dto.response.ProfileResponse;
import com.tikitaka.triptroop.user.dto.response.UserProfileResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@Transactional
@RequiredArgsConstructor
public class ProfileService {

    @Value("${image.image-url}")
    private String imageUrl;

    @Value("${image.image-dir}")
    private String imageDir;

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

    public UserProfileResponse findUserProfileByNickname(String nickname) {

        // TODO: Optional 체크
        final Profile profile = profileRepository.findByNickname(nickname);
        if (profile == null) {
            throw new NotFoundException(ExceptionCode.NOT_FOUND_USER_PROFILE);
        }

        final User user = userRepository.findById(profile.getUserId())
                                        .orElseThrow(() -> new NotFoundException(ExceptionCode.NOT_FOUND_USER));

        return UserProfileResponse.of(user, profile);
    }

    public ProfileResponse save(Long userId, ProfileSaveRequest profileRequest, MultipartFile profileImage) {
        return null;
    }


    public void update(ProfileSaveRequest profileRequest) { }

    public void uploadImage(Long userId, MultipartFile profileImage) { }

    public void deleteImage(Long userId) { }

}
