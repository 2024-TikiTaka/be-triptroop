package com.tikitaka.triptroop.chat.controller;

import com.tikitaka.triptroop.chat.domain.entity.ChatRoom;
import com.tikitaka.triptroop.chat.dto.request.ChatRequest;
import com.tikitaka.triptroop.chat.dto.response.ChatResponse;
import com.tikitaka.triptroop.chat.service.ChatRoomService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/chat")
@RequiredArgsConstructor
@Slf4j
public class ChatRoomController {

    private final ChatRoomService chatRoomService;

    @GetMapping("/chatroom")
    public ResponseEntity<List<ChatResponse>> getChatRoom() {
        List<ChatResponse> chatRoom = chatRoomService.getAllRooms();
        return ResponseEntity.ok(chatRoom);

    }

//    @PostMapping("/chatroom")
//    public ResponseEntity<ChatResponse> createChatRoom(@RequestBody @Valid final ChatRequest request) {
//        ChatResponse chatRoom = chatRoomService.createChatRoom(request);
//        return ResponseEntity.ok(chatRoom);
//    }

    /*
    * created_at -> db 저장 시간
    * room_name -> 조회한 프로필의 사용자의 닉네임
    * type -> 1:1 채팅 버튼을 눌렀다면 type = PRIVATE
    * creator -> 로그인 한 사람
    * member -> 조회한 프로필의 사용자
    * last_message_at -> 사용자가 채팅을 치면 입력됨.
    * */
}











