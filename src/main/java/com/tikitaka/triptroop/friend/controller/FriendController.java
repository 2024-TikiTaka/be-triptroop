package com.tikitaka.triptroop.friend.controller;

import com.tikitaka.triptroop.friend.dto.request.FriendAddRequest;
import com.tikitaka.triptroop.friend.dto.response.FriendAcceptorInfoResponse;
import com.tikitaka.triptroop.friend.service.FriendService;
import com.tikitaka.triptroop.user.domain.type.CustomUser;
import com.tikitaka.triptroop.user.dto.response.UserProfileResponse;
import com.tikitaka.triptroop.user.dto.response.UserResponse;
import com.tikitaka.triptroop.user.service.ProfileService;
import com.tikitaka.triptroop.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/friend")
@RequiredArgsConstructor
@Slf4j
public class FriendController {

    private final FriendService friendService;
    private final UserService userService;
    private final ProfileService profileService;

    /* 친구목록 조회 */
    @GetMapping
    public ResponseEntity<List<FriendAcceptorInfoResponse>> getFriends(@AuthenticationPrincipal CustomUser loginUser) {
        if (loginUser == null) {
            log.error("로그인된 사용자가 없습니다.");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        final UserResponse user = userService.findById(loginUser.getUserId());
        List<FriendAcceptorInfoResponse> friendList = friendService.getAcceptedFriends(user.getUserId());
        return ResponseEntity.ok(friendList);
    }

    /* 친구 신청 */
    @PostMapping("/request")
    public ResponseEntity<FriendAcceptorInfoResponse> requestFriend(@AuthenticationPrincipal CustomUser loginUser, @RequestBody FriendAddRequest request) {
        final UserProfileResponse userProfile = profileService.findUserProfileByNickname(request.getNickname());
        FriendAcceptorInfoResponse response = friendService.requestFriend(loginUser.getUserId(), userProfile.getUserId());
        return ResponseEntity.ok(response);
    }



}
