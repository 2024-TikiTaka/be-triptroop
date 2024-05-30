package com.tikitaka.triptroop.chat.service;

import com.tikitaka.triptroop.chat.domain.entity.ChatRoom;
import com.tikitaka.triptroop.chat.domain.repository.ChatRoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatRoomService {
    private final ChatRoomRepository chatRoomRepository;
    public List<ChatRoom> getAllRooms() {
        return chatRoomRepository.findAll();
    }
}
