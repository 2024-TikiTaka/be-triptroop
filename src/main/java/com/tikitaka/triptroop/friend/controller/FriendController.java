package com.tikitaka.triptroop.friend.controller;

import com.tikitaka.triptroop.friend.domain.entity.Friend;
import com.tikitaka.triptroop.friend.dto.response.FriendResponse;
import com.tikitaka.triptroop.friend.service.FriendService;
import com.tikitaka.triptroop.user.domain.type.CustomUser;
import com.tikitaka.triptroop.user.dto.response.UserResponse;
import com.tikitaka.triptroop.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/friend")
@RequiredArgsConstructor
@Slf4j
public class FriendController {

    private final FriendService friendService;
    private final UserService userService;

    /* 친구목록 조회 */
    @GetMapping
    public ResponseEntity<List<FriendResponse>> getFriends(@AuthenticationPrincipal CustomUser loginUser) {
        if (loginUser == null) {
            log.error("로그인된 사용자가 없습니다.");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        final UserResponse user = userService.findById(loginUser.getUserId());
        List<FriendResponse> friendList = friendService.getFriends(user.getUserId());
        return ResponseEntity.ok(friendList);
    }
}
