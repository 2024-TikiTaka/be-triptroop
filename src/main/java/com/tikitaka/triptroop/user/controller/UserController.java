package com.tikitaka.triptroop.user.controller;

import com.tikitaka.triptroop.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // @GetMapping("/{userId}")
    // public ResponseEntity<ApiResponse> getUser(@PathVariable Long userId) {
    //     return ResponseEntity.ok(
    //             ApiResponse.success(userService.findById(userId))
    //     );
    // }
}
