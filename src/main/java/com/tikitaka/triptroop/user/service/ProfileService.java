package com.tikitaka.triptroop.user.service;

import com.tikitaka.triptroop.common.exception.ConflictException;
import com.tikitaka.triptroop.common.exception.NotFoundException;
import com.tikitaka.triptroop.common.exception.type.ExceptionCode;
import com.tikitaka.triptroop.image.util.FileUploadUtils;
import com.tikitaka.triptroop.user.domain.entity.Profile;
import com.tikitaka.triptroop.user.domain.repository.ProfileRepository;
import com.tikitaka.triptroop.user.dto.request.ProfileSaveRequest;
import com.tikitaka.triptroop.user.dto.response.ProfileResponse;
import com.tikitaka.triptroop.user.dto.response.UserProfileResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ProfileService {

    @Value("${image.image-url}")
    private String imageUrl;

    @Value("${image.image-dir}")
    private String imageDir;

    private final ProfileRepository profileRepository;

    public boolean existsProfileByUserId(Long userId) {
        return profileRepository.existsProfileByUserId(userId);
    }

    public boolean existsByNickname(String nickname) {
        return profileRepository.existsByNickname(nickname);
    }

    public void checkNicknameDuplicate(String nickname) {
        if (existsByNickname(nickname)) {
            throw new ConflictException(ExceptionCode.ALREADY_EXISTS_NICKNAME);
        }
    }

    /**
     * 회원 번호로 프로필 정보 조회
     *
     * @return UserProfileResponse 회원번호, 나이(범위), 성별, 고도, 닉네임, 프로필이미지, 자기소개, MBTI
     */
    @Transactional(readOnly = true)
    public UserProfileResponse findUserProfileByUserId(Long userId) {

        return profileRepository.findUserProfileByUserId(userId)
                                .orElseThrow(() -> new NotFoundException(ExceptionCode.NOT_FOUND_USER_PROFILE));
    }

    /**
     * 닉네임으로 프로필 정보 조회
     *
     * @return UserProfileResponse 회원번호, 나이(범위), 성별, 고도, 닉네임, 프로필이미지, 자기소개, MBTI
     */
    @Transactional(readOnly = true)
    public UserProfileResponse findUserProfileByNickname(String nickname) {

        return profileRepository.findUserProfileByNickname(nickname)
                                .orElseThrow(() -> new NotFoundException(ExceptionCode.NOT_FOUND_USER_PROFILE));
    }

    @Transactional
    public ProfileResponse save(Long userId, ProfileSaveRequest profileRequest, MultipartFile profileImage) {

        if (existsByNickname(profileRequest.getNickname())) {
            throw new ConflictException(ExceptionCode.ALREADY_EXISTS_NICKNAME);
        }

        String profileImageUrl = FileUploadUtils.uploadFile(imageDir, profileImage);
        final Profile newProfile = Profile.of(userId,
                                              profileRequest.getNickname(),
                                              imageUrl + profileImageUrl,
                                              profileRequest.getIntroduction(),
                                              profileRequest.getMbti());

        profileRepository.save(newProfile);

        return ProfileResponse.from(newProfile);
    }

    @Transactional
    public ProfileResponse updateProfile(Long userId, ProfileSaveRequest profileRequest) {

        if (existsByNickname(profileRequest.getNickname())) {
            throw new ConflictException(ExceptionCode.ALREADY_EXISTS_NICKNAME);
        }

        final Profile profile = profileRepository.findProfileByUserId(userId)
                                                 .orElseThrow(() -> new NotFoundException(ExceptionCode.NOT_FOUND_USER_PROFILE));

        profile.updateProfile(profileRequest.getNickname(), profileRequest.getIntroduction(), profileRequest.getMbti());

        return ProfileResponse.from(profile);
    }

    @Transactional
    public void uploadImage(Long userId, MultipartFile image) {

        final Profile profile = profileRepository.findProfileByUserId(userId)
                                                 .orElseThrow(() -> new NotFoundException(ExceptionCode.NOT_FOUND_USER_PROFILE));
        String profileImag = FileUploadUtils.uploadFile(imageDir, image);
        String storedProfileImage = imageUrl + profileImag;

        profile.updateProfileImage(storedProfileImage);
    }

    @Transactional
    public void deleteImage(Long userId) {

        final Profile profile = profileRepository.findProfileByUserId(userId)
                                                 .orElseThrow(() -> new NotFoundException(ExceptionCode.NOT_FOUND_USER_PROFILE));
        if (profile.getProfileImage() == null) {
            throw new NotFoundException(ExceptionCode.NOT_FOUND_IMAGE);
        }

        String storedFilename = profile.getProfileImage().replaceAll(imageUrl, "");
        FileUploadUtils.deleteFile(imageDir, storedFilename);

        profile.deleteProfileImage();
    }
}
