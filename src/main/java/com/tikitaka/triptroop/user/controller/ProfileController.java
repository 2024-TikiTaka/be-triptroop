package com.tikitaka.triptroop.user.controller;

import com.tikitaka.triptroop.user.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/profiles")
@RequiredArgsConstructor
public class ProfileController {

    private final ProfileService profileService;
    //
    // @GetMapping
    // public ResponseEntity<ApiResponse> getMyUserProfile(@RequestParam Long userId) {
    //
    //     return ResponseEntity.ok(
    //             ApiResponse.success(profileService.findByUserId(userId))
    //     );
    // }
    //
}
