package com.tikitaka.triptroop.chat.controller;

import com.tikitaka.triptroop.chat.domain.entity.ChatRoom;
import com.tikitaka.triptroop.chat.dto.response.ChatResponse;
import com.tikitaka.triptroop.chat.service.ChatRoomService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public List<ChatResponse> getChatRoom() {
        List<ChatRoom> chatRooms = chatRoomService.getAllRooms();
        log.info(chatRooms.toString());
        return chatRooms.stream()
                .map(room -> {
                    ChatResponse response = new ChatResponse();
                    response.setId(room.getId().toHexString());
                    response.setRoomName(room.getRoomName());
                    response.setInvitor(room.getInvitor());
                    response.setParticipants(room.getParticipants());
                    response.setCreatedAt(room.getCreatedAt().format(DateTimeFormatter.ISO_DATE_TIME));
                    return response;
                })
                .collect(Collectors.toList());
    }
}
