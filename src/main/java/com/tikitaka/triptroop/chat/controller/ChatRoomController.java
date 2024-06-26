package com.tikitaka.triptroop.chat.controller;

import com.tikitaka.triptroop.chat.dto.request.ChatRoomCreateRequest;
import com.tikitaka.triptroop.chat.dto.response.ChatResponse;
import com.tikitaka.triptroop.chat.service.ChatRoomService;
import com.tikitaka.triptroop.user.domain.type.CustomUser;
import com.tikitaka.triptroop.user.dto.response.UserResponse;
import com.tikitaka.triptroop.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/chat")
@RequiredArgsConstructor
@Slf4j
public class ChatRoomController {

    private final ChatRoomService chatRoomService;
    private final UserService userService;

    @GetMapping("/chatroom")
    public ResponseEntity<List<ChatResponse>> getChatRoom(@AuthenticationPrincipal CustomUser loginUser) {
        if (loginUser == null) {
            log.error("로그인된 사용자가 없습니다.");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        final UserResponse user = userService.findById(loginUser.getUserId());
        List<ChatResponse> chatRoom = chatRoomService.getUserRooms(user.getUserId());
        return ResponseEntity.ok(chatRoom);
    }

    @PostMapping("/chatroom")
    public ResponseEntity<ChatResponse> createChatRoom(@RequestBody @Valid final ChatRoomCreateRequest request,
                                                       @AuthenticationPrincipal CustomUser loginUser) {
        ChatResponse chatRoom = chatRoomService.createChatRoom(request, loginUser);
        return ResponseEntity.ok(chatRoom);
    }
}











