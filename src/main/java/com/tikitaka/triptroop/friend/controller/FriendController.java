package com.tikitaka.triptroop.friend.controller;

import com.tikitaka.triptroop.common.dto.response.ApiResponse;
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

    /* 친구 목록 조회 */
    @GetMapping
    public ResponseEntity<ApiResponse<List<FriendAcceptorInfoResponse>>> getFriends(@AuthenticationPrincipal CustomUser loginUser) {
        final UserResponse user = userService.findById(loginUser.getUserId());
        List<FriendAcceptorInfoResponse> friendList = friendService.getAcceptedFriends(user.getUserId());
        return ResponseEntity.ok(ApiResponse.success(friendList));
    }

    /* 친구 신청 */
    @PostMapping("/request")
    public ResponseEntity<ApiResponse<FriendAcceptorInfoResponse>> requestFriend(@AuthenticationPrincipal CustomUser loginUser, @RequestBody FriendAddRequest request) {
        final UserProfileResponse userProfile = profileService.findUserProfileByNickname(request.getNickname());
        final FriendAcceptorInfoResponse friendAcceptorInfoResponse = friendService.requestFriend(loginUser.getUserId(), userProfile.getUserId());
        /* 알림 service로 acceptor, requester id 보내기
        * 알림 service에서는 db에 저장하고 content와 acceptor id를 반환 */
        return ResponseEntity.ok(ApiResponse.success(friendAcceptorInfoResponse));
    }

    /* 친구 신청 수락 */
    @PostMapping("/request/accept")
    public ResponseEntity<ApiResponse<FriendAcceptorInfoResponse>> acceptFriendRequest(@AuthenticationPrincipal CustomUser loginUser, @RequestBody FriendAddRequest request) {
        final UserProfileResponse userProfile = profileService.findUserProfileByNickname(request.getNickname());
        /* 채팅으로 받은 알림 메시지에서 requester id 받기 */
        final FriendAcceptorInfoResponse friendAcceptorInfoResponse = friendService.acceptFriend(loginUser.getUserId(), userProfile.getUserId());
        return ResponseEntity.ok(ApiResponse.success(friendAcceptorInfoResponse));
    }

//    /* 친구 신청 거절 */
//    @PostMapping("/request/reject")
//    public ResponseEntity<ApiResponse<>> rejectFriendRequest() {
//
//    }



}
