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
    public ResponseEntity<ApiResponse> checkDuplicateNickname(String nickname) {

        profileService.checkNicknameDuplicate(nickname);
        return ResponseEntity.ok(ApiResponse.success("사용 가능한 닉네임입니다."));
    }

    /**
     * 닉네임으로 프로필 조회
     *
     * @param nickname 닉네임
     * @return UserProfileResponse 성별, 나이(구간), 고도, 프로필번호, 프로필이미지, 닉네임, 자기소개, MBTI
     */
    // TODO: 성향, 친구여부, 차단여부, 채팅 링크
    @GetMapping("/profiles")
    public ResponseEntity<ApiResponse> getProfileByNickname(@AuthenticationPrincipal CustomUser loginUser, String nickname) {

        final UserProfileResponse userProfile = profileService.findUserProfileByNickname(nickname);
        return ResponseEntity.ok(ApiResponse.success(userProfile));
    }

    /**
     * 내 프로필 조회
     *
     * @param loginUser 로그인 정보
     * @return ProfileResponse 프로필번호, 프로필이미지, 닉네임, 자기소개, MBTI, 고도
     */
    @GetMapping("/users/me/profile")
    public ResponseEntity<ApiResponse> getProfile(@AuthenticationPrincipal CustomUser loginUser) {

        final UserProfileResponse userProfile = profileService.findUserProfileByUserId(loginUser.getUserId());
        return ResponseEntity.ok(ApiResponse.success(userProfile));
    }

    /**
     * 내 프로필 등록
     *
     * @param loginUser      로그인 정보
     * @param profileRequest 닉네임, 자기소개, MBTI
     */
    @PostMapping("/users/me/profile")
    public ResponseEntity<ApiResponse> saveProfile(@AuthenticationPrincipal CustomUser loginUser,
                                                   @RequestPart @Valid ProfileSaveRequest profileRequest,
                                                   @RequestPart MultipartFile profileImage) {

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
    @PutMapping("/users/me/profile")
    public ResponseEntity<ApiResponse> updateProfile(@AuthenticationPrincipal CustomUser loginUser,
                                                     @ModelAttribute @Valid ProfileSaveRequest profileRequest) {

        profileService.update(loginUser.getUserId(), profileRequest);
        return ResponseEntity.ok(ApiResponse.success());
    }

    /**
     * 내 프로필 이미지 등록
     *
     * @param loginUser    로그인 정보
     * @param profileImage 닉네임, 자기소개, MBTI
     */
    @PostMapping("/users/me/profile/upload")
    public ResponseEntity<ApiResponse> uploadProfileImage(@AuthenticationPrincipal CustomUser loginUser,
                                                          @RequestBody MultipartFile profileImage) {

        profileService.uploadImage(loginUser.getUserId(), profileImage);
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.success());
    }

    /**
     * 내 프로필 이미지 삭제
     *
     * @param loginUser 로그인 정보
     */
    @DeleteMapping("/users/me/profile/upload")
    public ResponseEntity<ApiResponse<Void>> deleteProfileImage(@AuthenticationPrincipal CustomUser loginUser) {

        profileService.deleteImage(loginUser.getUserId());
        return ResponseEntity.noContent().build();
    }
}
