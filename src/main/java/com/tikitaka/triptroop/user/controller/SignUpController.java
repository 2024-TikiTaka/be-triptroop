package com.tikitaka.triptroop.user.controller;

import com.tikitaka.triptroop.common.dto.response.ApiResponse;
import com.tikitaka.triptroop.user.dto.request.SignUpRequest;
import com.tikitaka.triptroop.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1/signup")
@RequiredArgsConstructor
public class SignUpController {

    private final UserService userService;

    @PostMapping()
    public ResponseEntity<ApiResponse<Void>> signUp(@RequestBody @Valid SignUpRequest signUpRequest) {
        userService.signup(signUpRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.success("회원가입이 완료되었습니다."));
    }

}
