package com.tikitaka.triptroop.user.controller;

import com.tikitaka.triptroop.common.dto.response.ApiResponse;
import com.tikitaka.triptroop.common.exception.ConflictException;
import com.tikitaka.triptroop.common.exception.type.ExceptionCode;
import com.tikitaka.triptroop.user.domain.type.CustomUser;
import com.tikitaka.triptroop.user.dto.request.ProfileSaveRequest;
import com.tikitaka.triptroop.user.dto.response.ProfileResponse;
import com.tikitaka.triptroop.user.dto.response.UserProfileResponse;
import com.tikitaka.triptroop.user.service.ProfileService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class ProfileController {

    private final ProfileService profileService;

    /**
     * 닉네임 중복 여부
     *
     * @param nickname 닉네임
     */
    @GetMapping("/check/nickname")
    public ResponseEntity<ApiResponse<?>> checkDuplicateNickname(String nickname) {

        profileService.checkNicknameDuplicate(nickname);

        return ResponseEntity.ok(
                ApiResponse.success("사용 가능한 닉네임입니다.")
        );
    }

    /**
     * 프로필 조회
     *
     * @param profileId 프로필번호
     * @return ProfileResponse
     */
    @GetMapping("/profiles/{profileId}")
    public ResponseEntity<ApiResponse<ProfileResponse>> getProfile(@PathVariable("profileId") Long profileId) {

        return ResponseEntity.ok(
                ApiResponse.success(profileService.findById(profileId))
        );
    }


    /**
     * 프로필 상세 조회
     *
     * @param userId   회원번호
     * @param nickname 닉네임
     * @return UserProfileResponse 회원번호, 성별, 나이(구간), 고도, 프로필:{프로필번호, 프로필이미지, 닉네임, 자기소개, MBTI}
     */
    @GetMapping("/users/profile")
    public ResponseEntity<ApiResponse<UserProfileResponse>> getUserProfile(@RequestParam(required = false) Long userId,
                                                                           @RequestParam(required = false) String nickname) {

        UserProfileResponse user = null;

        if (userId != null) {
            user = profileService.findUserProfileByUserId(userId);
        }
        if (nickname != null) {
            user = profileService.findUserProfileByNickname(nickname);
        }

        return ResponseEntity.ok(
                ApiResponse.success(user)
        );
    }

    /* ================================================== */

    /**
     * 내 프로필 조회
     *
     * @param loginUser 로그인 정보
     * @return ProfileResponse 프로필번호, 프로필이미지, 닉네임, 자기소개, MBTI, 고도
     */
    @GetMapping("/profiles/me")
    public ResponseEntity<ApiResponse<ProfileResponse>> getProfile(@AuthenticationPrincipal CustomUser loginUser) {

        return ResponseEntity.ok(
                ApiResponse.success(profileService.findByUserId(loginUser.getUserId()))
        );
    }

    /**
     * 내 프로필 상세 조회
     *
     * @param loginUser 로그인 정보
     * @return UserProfileResponse 회원번호, 성별, 나이(구간), 고도, 프로필:{프로필번호, 프로필이미지, 닉네임, 자기소개, MBTI}
     */
    @GetMapping("/users/me/profile")
    public ResponseEntity<ApiResponse<UserProfileResponse>> getUserProfile(@AuthenticationPrincipal CustomUser loginUser) {

        UserProfileResponse currentUser = profileService.findUserProfileByUserId(loginUser.getUserId());
        return ResponseEntity.ok(ApiResponse.success(currentUser));
    }

    /**
     * 내 프로필 등록
     *
     * @param loginUser      로그인 정보
     * @param profileRequest 닉네임, 자기소개, MBTI
     */
    @PostMapping("/profiles/me")
    public ResponseEntity<ApiResponse<?>> saveProfile(@AuthenticationPrincipal CustomUser loginUser,
                                                      @RequestPart @Valid ProfileSaveRequest profileRequest,
                                                      @RequestPart MultipartFile profileImage) throws IOException {

        Long userId = loginUser.getUserId();

        if (profileService.existsProfileByUserId(userId)) {
            throw new ConflictException(ExceptionCode.ALREADY_EXISTS_PROFILE);
        }
        final ProfileResponse profile = profileService.save(userId, profileRequest, profileImage);
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.success(profile));
    }

    /**
     * 내 프로필 수정
     *
     * @param loginUser      로그인 정보
     * @param profileRequest 닉네임, 자기소개, MBTI
     */
    @PutMapping("/profiles/me")
    public ResponseEntity<ApiResponse<?>> updateProfile(@AuthenticationPrincipal CustomUser loginUser,
                                                        @RequestBody @Valid ProfileSaveRequest profileRequest) {

        final ProfileResponse profile = profileService.updateProfile(loginUser.getUserId(), profileRequest);
        return ResponseEntity.ok(ApiResponse.success("프로필 수정이 완료되었습니다.", profile));
    }

    /**
     * 내 프로필 이미지 등록
     *
     * @param loginUser    로그인 정보
     * @param profileImage 닉네임, 자기소개, MBTI
     */
    @PostMapping("/profiles/me/upload")
    public ResponseEntity<ApiResponse<?>> uploadProfileImage(@AuthenticationPrincipal CustomUser loginUser,
                                                             @RequestBody MultipartFile profileImage) {

        profileService.uploadImage(loginUser.getUserId(), profileImage);
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.success("이미지 등록이 완료되었습니다."));
    }

    /**
     * 내 프로필 이미지 삭제
     *
     * @param loginUser 로그인 정보
     */
    @DeleteMapping("/profiles/me/upload")
    public ResponseEntity<ApiResponse<?>> deleteProfileImage(@AuthenticationPrincipal CustomUser loginUser) {

        profileService.deleteImage(loginUser.getUserId());
        return ResponseEntity.noContent().build();
    }
}
