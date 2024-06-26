package com.tikitaka.triptroop.user.controller;


import com.tikitaka.triptroop.common.dto.response.ApiResponse;
import com.tikitaka.triptroop.user.domain.type.CustomUser;
import com.tikitaka.triptroop.user.dto.request.SignUpRequest;
import com.tikitaka.triptroop.user.service.AuthService;
import com.tikitaka.triptroop.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    private final AuthService authService;

    /**
     * 토큰 재발급
     */
    @GetMapping("/token/issue")
    public ResponseEntity<?> issueToken(@RequestHeader("Refresh-Token") String refreshToken,
                                        @AuthenticationPrincipal CustomUser loginUser) {

        String ReIssuedAccessToken = authService.issueToken(refreshToken, loginUser.getUsername());

        return ResponseEntity.noContent()
                             .header("Access-Token", ReIssuedAccessToken)
                             .build();
    }

    /**
     * 비밀번호 재설정
     *
     * @param email    이메일
     * @param password 비밀번호
     */
    @PostMapping("/password/reset")
    public ResponseEntity<ApiResponse<?>> resetPassword(String email, String password) {

        userService.resetPassword(email, password);
        return ResponseEntity.ok(ApiResponse.success("비밀번호 변경이 완료되었습니다."));
    }


    /**
     * 회원가입
     *
     * @param signUpRequest 이메일, 비밀번호, 이름, 생년월일, 성별
     */
    @PostMapping("/signup")
    public ResponseEntity<ApiResponse<?>> signup(@Valid @RequestBody SignUpRequest signUpRequest) {

        userService.signup(signUpRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.success("회원가입이 완료되었습니다."));
    }

    /**
     * 로그아웃
     *
     * @param loginUser 로그인 정보
     */
    @PostMapping("/logout")
    public ResponseEntity<ApiResponse<?>> logout(@AuthenticationPrincipal CustomUser loginUser) {

        authService.updateRefreshToken(loginUser.getUsername(), null);
        return ResponseEntity.ok(ApiResponse.success("로그아웃이 완료되었습니다."));
    }
}
