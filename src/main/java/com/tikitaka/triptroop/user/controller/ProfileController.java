package com.tikitaka.triptroop.user.controller;

import com.tikitaka.triptroop.common.dto.response.ApiResponse;
import com.tikitaka.triptroop.user.domain.type.CustomUser;
import com.tikitaka.triptroop.user.dto.request.ProfileSaveRequest;
import com.tikitaka.triptroop.user.service.ProfileService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequestMapping("/api/v1/")
@RequiredArgsConstructor
public class ProfileController {

    private final ProfileService profileService;

    /**
     * 닉네임으로 프로필 조회
     *
     * @param nickname 닉네임
     * @return UserProfileResponse 성별, 나이(구간), 고도, 프로필번호, 프로필이미지, 닉네임, 자기소개, MBTI
     */
    // TODO: 성향, 친구여부, 차단여부, 채팅 링크
    @GetMapping("/users/profile")
    public ResponseEntity<ApiResponse> getProfile(String nickname) {
        return ResponseEntity.ok(ApiResponse.success());
    }

    /**
     * 내 프로필 조회
     *
     * @param loginUser 로그인 정보
     * @return ProfileResponse 프로필번호, 프로필이미지, 닉네임, 자기소개, MBTI, 고도
     */
    @GetMapping("/users/me/profile")
    public ResponseEntity<ApiResponse> getMyProfile(@AuthenticationPrincipal CustomUser loginUser) {
        return ResponseEntity.ok(ApiResponse.success());
    }

    /**
     * 내 프로필 등록
     *
     * @param loginUser      로그인 정보
     * @param profileRequest 닉네임, 자기소개, MBTI
     */
    @PostMapping("/users/me/profile")
    public ResponseEntity<ApiResponse<Void>> createMyProfile(@AuthenticationPrincipal CustomUser loginUser,
                                                             @ModelAttribute @Valid ProfileSaveRequest profileRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.success());
    }

    /**
     * 내 프로필 수정
     *
     * @param loginUser      로그인 정보
     * @param profileRequest 닉네임, 자기소개, MBTI
     */
    @PutMapping("/users/me/profile")
    public ResponseEntity<ApiResponse> updateMyProfile(@AuthenticationPrincipal CustomUser loginUser,
                                                       @ModelAttribute @Valid ProfileSaveRequest profileRequest) {
        return ResponseEntity.ok(ApiResponse.success());
    }

    /**
     * 내 프로필 이미지 등록
     *
     * @param loginUser    로그인 정보
     * @param profileImage 닉네임, 자기소개, MBTI
     */
    @PutMapping("/users/me/profile/upload")
    public ResponseEntity<ApiResponse> uploadMyProfileImage(@AuthenticationPrincipal CustomUser loginUser,
                                                            @RequestBody MultipartFile profileImage) {
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.success());
    }

    /**
     * 내 프로필 이미지 삭제
     *
     * @param loginUser 로그인 정보
     */
    @DeleteMapping("/users/me/profile/upload")
    public ResponseEntity<ApiResponse<Void>> deleteMyProfileImage(@AuthenticationPrincipal CustomUser loginUser) {
        return ResponseEntity.noContent().build();
    }
}






