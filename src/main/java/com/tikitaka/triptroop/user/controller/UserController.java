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
    //
    // @GetMapping("/me")
    // public ResponseEntity<ApiResponse> getMyUserProfile(@AuthenticationPrincipal CustomUser loginUser) {
    //     UserDetailResponse userDetail = userService.findById(loginUser.getUserId());
    //     return ResponseEntity.ok(ApiResponse.success(userDetail));
    // }
    //
    // @PostMapping("/me/password/check")
    // public ResponseEntity<ApiResponse> verifyPassword() {
    //     return ResponseEntity.ok(
    //             ApiResponse.success(null)
    //     );
    // }
    //
    // @GetMapping("/{userId}")
    // public ResponseEntity<ApiResponse> getUserByUserId(@PathVariable Long userId) {
    //     return ResponseEntity.ok(
    //             ApiResponse.success(null)
    //     );
    // }

}
