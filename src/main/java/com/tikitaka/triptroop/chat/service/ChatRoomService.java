package com.tikitaka.triptroop.chat.service;

import com.tikitaka.triptroop.chat.domain.entity.ChatRoom;
import com.tikitaka.triptroop.chat.domain.repository.ChatRoomRepository;
import com.tikitaka.triptroop.chat.dto.request.ChatRequest;
import com.tikitaka.triptroop.chat.dto.response.ChatResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ChatRoomService {
    private final ChatRoomRepository chatRoomRepository;
    public List<ChatResponse> getAllRooms() {
        List<ChatRoom> chatRoom = chatRoomRepository.findAll();
        return chatRoom.stream()
                .map(ChatResponse::from)
                .collect(Collectors.toList());
    }

    public ChatResponse createChatRoom(@Valid ChatRequest request) {
        ChatRoom chatRoom = chatRoomRepository.createChatRoom(request);
        return ChatResponse.from(chatRoom);
    }
}
