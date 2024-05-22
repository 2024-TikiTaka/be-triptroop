package com.tikitaka.triptroop.user.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    @RequestMapping()
    public ResponseEntity<String> hello() {
        return ResponseEntity.ok("Hello, User!");
    }
}
