package com.tikitaka.triptroop.common.email.controller;

import com.tikitaka.triptroop.common.dto.response.ApiResponse;
import com.tikitaka.triptroop.common.email.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/")
@RequiredArgsConstructor
public class EmailController {

    private final EmailService emailService;

    /**
     * 인증 번호 전송
     *
     * @param email
     */
    @PostMapping("/email/send")
    public ResponseEntity<ApiResponse<?>> send(String email) throws Exception {

        String authCode = emailService.generateAuthCode();
        String token = emailService.encryptToken(email, authCode);
        emailService.sendVerificationEmail(email, authCode);

        return ResponseEntity.ok(
                ApiResponse.success("인증 번호가 발송되었습니다.", token)
        );
    }

    /**
     * 인증 번호 검증
     *
     * @param token
     * @param email
     * @param code
     */
    @PostMapping("/email/verify")
    public ResponseEntity<ApiResponse<?>> verify(String token, String email, String code) {

        emailService.verifyToken(token, email, code);
        return ResponseEntity.ok(ApiResponse.success("인증 완료되었습니다."));
    }
}
