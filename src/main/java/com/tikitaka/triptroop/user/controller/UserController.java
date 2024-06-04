package com.tikitaka.triptroop.user.controller;

import com.tikitaka.triptroop.common.dto.response.ApiResponse;
import com.tikitaka.triptroop.user.domain.type.CustomUser;
import com.tikitaka.triptroop.user.dto.request.PasswordRequest;
import com.tikitaka.triptroop.user.dto.request.UserSaveRequest;
import com.tikitaka.triptroop.user.dto.response.UserResponse;
import com.tikitaka.triptroop.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    /**
     * 이메일 중복 여부
     *
     * @param email 이메일
     */
    @GetMapping("/check/email")
    public ResponseEntity<ApiResponse<Void>> checkDuplicateEmail(String email) {

        userService.checkEmailDuplicate(email);
        return ResponseEntity.ok(ApiResponse.success("사용 가능한 이메일입니다."));
    }

    /**
     * 내 정보 조회
     *
     * @param loginUser 로그인 정보
     * @return UserResponse 회원번호, 이메일, 이름, 성별, 생년월일, 전화번호, 가입일
     */
    @GetMapping("/users/me")
    public ResponseEntity<ApiResponse> getUser(@AuthenticationPrincipal CustomUser loginUser) {

        final UserResponse user = userService.findById(loginUser.getUserId());
        return ResponseEntity.ok(ApiResponse.success(user));
    }

    /**
     * 내 정보 수정
     *
     * @param loginUser   로그인 정보
     * @param userRequest 회원번호, 이메일, 이름, 성별, 생년월일, 전화번호
     *                    => 전화번호만 우선 수정 가능 TODO: 인증 후 저장
     */
    @PutMapping("/users/me")
    public ResponseEntity<ApiResponse> updateUser(@AuthenticationPrincipal CustomUser loginUser,
                                                  @ModelAttribute @Valid UserSaveRequest userRequest) {

        final UserResponse user = userService.updateUser(loginUser.getUserId(), userRequest);
        return ResponseEntity.ok(ApiResponse.success(user));
    }

    /**
     * 내 비밀번호 변경
     *
     * @param loginUser       로그인 정보
     * @param passwordRequest 기존 비밀번호, 새로운 비밀번호
     */
    @PostMapping("/users/me/password")
    public ResponseEntity<ApiResponse> changePassword(@AuthenticationPrincipal CustomUser loginUser,
                                                      @RequestBody @Valid PasswordRequest passwordRequest) {

        userService.changePassword(loginUser.getUserId(), passwordRequest);
        return ResponseEntity.ok(ApiResponse.success("비밀번호 변경이 완료되었습니다."));
    }

    /**
     * 회원 탈퇴
     *
     * @param loginUser 로그인 정보
     * @param password  비밀번호
     */
    @DeleteMapping("/users/me")
    public ResponseEntity<ApiResponse<Void>> deleteUser(@AuthenticationPrincipal CustomUser loginUser, String password) {

        userService.withdrawal(loginUser.getUserId(), password);
        return ResponseEntity.noContent().build();
    }
}
