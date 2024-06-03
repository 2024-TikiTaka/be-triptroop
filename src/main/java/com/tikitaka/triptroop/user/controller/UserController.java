package com.tikitaka.triptroop.user.controller;

import com.tikitaka.triptroop.common.dto.response.ApiResponse;
import com.tikitaka.triptroop.user.domain.type.CustomUser;
import com.tikitaka.triptroop.user.dto.request.PasswordRequest;
import com.tikitaka.triptroop.user.dto.request.UserSaveRequest;
import com.tikitaka.triptroop.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    /**
     * 회원 목록 조회
     *
     * @return 회원번호, 이메일, 이름, 성별, 생년월일, 전화번호, 가입일
     */
    @GetMapping
    public ResponseEntity<ApiResponse> getAllUsers() {
        return ResponseEntity.ok(ApiResponse.success());
    }

    /**
     * 내 정보 조회
     *
     * @param loginUser 로그인 정보
     * @return UserResponse 회원번호, 이메일, 이름, 성별, 생년월일, 전화번호, 가입일
     */
    @GetMapping("/me")
    public ResponseEntity<ApiResponse> getUser(@AuthenticationPrincipal CustomUser loginUser) {
        return ResponseEntity.ok(ApiResponse.success());
    }

    /**
     * 내 정보 수정
     *
     * @param loginUser   로그인 정보
     * @param userRequest 회원번호, 이메일, 이름, 성별, 생년월일, 전화번호
     */
    @PutMapping("/me")
    public ResponseEntity<ApiResponse> updateUser(@AuthenticationPrincipal CustomUser loginUser,
                                                  @ModelAttribute @Valid UserSaveRequest userRequest) {
        return ResponseEntity.ok(ApiResponse.success());
    }

    /**
     * 내 비밀번호 변경
     *
     * @param loginUser       로그인 정보
     * @param passwordRequest 기존 비밀번호, 새로운 비밀번호
     */
    @PostMapping("/me/password")
    public ResponseEntity<ApiResponse> changePassword(@AuthenticationPrincipal CustomUser loginUser,
                                                      @RequestBody @Valid PasswordRequest passwordRequest) {
        return ResponseEntity.ok(ApiResponse.success());
    }

    /**
     * 회원 탈퇴
     *
     * @param loginUser 로그인 정보
     * @param password  비밀번호
     */
    @DeleteMapping("/me")
    public ResponseEntity<ApiResponse<Void>> deleteUser(@AuthenticationPrincipal CustomUser loginUser, String password) {
        return ResponseEntity.noContent().build();
    }
}
