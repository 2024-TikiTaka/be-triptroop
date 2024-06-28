package com.tikitaka.triptroop.user.controller;


import com.tikitaka.triptroop.user.service.AuthService;
import com.tikitaka.triptroop.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class OAuth2Controller {

    @PostMapping("/login/oauth2/{provider}")
    public void socialLogin(@PathVariable String provider) {
        log.info("✅OAuth2Controller.socialLogin");
        log.info("💬provider : {} ", provider);
    }
}
