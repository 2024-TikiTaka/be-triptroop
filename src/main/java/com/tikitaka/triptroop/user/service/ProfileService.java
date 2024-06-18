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


@Service
@Transactional
@RequiredArgsConstructor
public class ProfileService {

    @Value("${image.image-url}")
    private String imageUrl;

    @Value("${image.image-dir}")
    private String imageDir;

    private final ProfileRepository profileRepository;

    /**
     * 프로필 존재 여부
     *
     * @param userId 회원번호
     */
    public boolean existsProfileByUserId(Long userId) {
        return profileRepository.existsProfileByUserId(userId);
    }

    /**
     * 프로필 존재 여부
     *
     * @param nickname 닉네임
     */
    public boolean existsByNickname(String nickname) {
        return profileRepository.existsByNickname(nickname);
    }

    /**
     * 닉네임 중복 검증
     *
     * @param nickname 닉네임
     */
    public void checkNicknameDuplicate(String nickname) {
        if (existsByNickname(nickname)) {
            throw new ConflictException(ExceptionCode.ALREADY_EXISTS_NICKNAME);
        }
    }

    /**
     * 프로필 상세 조회
     *
     * @param profileId 프로필번호
     * @return UserProfileResponse 회원번호, 성별, 나이(구간), 고도, 프로필:{프로필번호, 프로필이미지, 닉네임, 자기소개, MBTI}
     */
    @Transactional(readOnly = true)
    public UserProfileResponse findUserProfileById(Long profileId) {

        return profileRepository.findUserProfileById(profileId)
                                .orElseThrow(() -> new NotFoundException(ExceptionCode.NOT_FOUND_USER_PROFILE));
    }

    /**
     * 프로필 상세 조회
     *
     * @param userId 회원번호
     * @return UserProfileResponse 회원번호, 성별, 나이(구간), 고도, 프로필:{프로필번호, 프로필이미지, 닉네임, 자기소개, MBTI}
     */
    @Transactional(readOnly = true)
    public UserProfileResponse findUserProfileByUserId(Long userId) {

        return profileRepository.findUserProfileByUserId(userId)
                                .orElseThrow(() -> new NotFoundException(ExceptionCode.NOT_FOUND_USER_PROFILE));
    }

    /**
     * 프로필 상세 조회
     *
     * @param nickname 닉네임
     * @return UserProfileResponse 회원번호, 성별, 나이(구간), 고도, 프로필:{프로필번호, 프로필이미지, 닉네임, 자기소개, MBTI}
     */
    @Transactional(readOnly = true)
    public UserProfileResponse findUserProfileByNickname(String nickname) {

        return profileRepository.findUserProfileByNickname(nickname)
                                .orElseThrow(() -> new NotFoundException(ExceptionCode.NOT_FOUND_USER_PROFILE));
    }

    /**
     * 프로필 조회
     *
     * @param profileId 프로필번호
     * @return ProfileResponse
     */
    @Transactional(readOnly = true)
    public ProfileResponse findById(Long profileId) {

        return ProfileResponse.from(
                profileRepository.findById(profileId)
                                 .orElseThrow(() -> new NotFoundException(ExceptionCode.NOT_FOUND_USER_PROFILE))
        );
    }

    /**
     * 프로필 조회
     *
     * @param userId 회원번호
     * @return ProfileResponse
     */
    @Transactional(readOnly = true)
    public ProfileResponse findByUserId(Long userId) {

        return ProfileResponse.from(
                profileRepository.findByUserId(userId)
                                 .orElseThrow(() -> new NotFoundException(ExceptionCode.NOT_FOUND_USER_PROFILE))
        );
    }

    /**
     * 프로필 등록
     *
     * @param userId
     * @param profileRequest
     * @param profileImage
     */
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

    /**
     * 프로필 수정
     *
     * @param userId
     * @param profileRequest
     */
    @Transactional
    public ProfileResponse updateProfile(Long userId, ProfileSaveRequest profileRequest) {

        if (existsByNickname(profileRequest.getNickname())) {
            throw new ConflictException(ExceptionCode.ALREADY_EXISTS_NICKNAME);
        }

        final Profile profile = profileRepository.findByUserId(userId)
                                                 .orElseThrow(() -> new NotFoundException(ExceptionCode.NOT_FOUND_USER_PROFILE));

        profile.updateProfile(profileRequest.getNickname(), profileRequest.getIntroduction(), profileRequest.getMbti());

        return ProfileResponse.from(profile);
    }


    /**
     * 프로필 이미지 등록
     *
     * @param userId
     * @param image
     */
    @Transactional
    public void uploadImage(Long userId, MultipartFile image) {

        final Profile profile = profileRepository.findByUserId(userId)
                                                 .orElseThrow(() -> new NotFoundException(ExceptionCode.NOT_FOUND_USER_PROFILE));
        String profileImag = FileUploadUtils.uploadFile(imageDir, image);
        String storedProfileImage = imageUrl + profileImag;

        profile.updateProfileImage(storedProfileImage);
    }

    /**
     * 프로필 이미지 삭제
     *
     * @param userId
     */
    @Transactional
    public void deleteImage(Long userId) {

        final Profile profile = profileRepository.findByUserId(userId)
                                                 .orElseThrow(() -> new NotFoundException(ExceptionCode.NOT_FOUND_USER_PROFILE));

        if (profile.getProfileImage() == null) {
            throw new NotFoundException(ExceptionCode.NOT_FOUND_IMAGE);
        }

        String storedFilename = profile.getProfileImage().replaceAll(imageUrl, "");
        FileUploadUtils.deleteFile(imageDir, storedFilename);

        profile.deleteProfileImage();
    }
}
