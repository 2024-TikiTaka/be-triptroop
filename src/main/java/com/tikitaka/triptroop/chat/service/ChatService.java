package com.tikitaka.triptroop.chat.service;

import com.tikitaka.triptroop.chat.domain.entity.Message;
import com.tikitaka.triptroop.chat.domain.repository.ChatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ChatService {
    private final ChatRepository chatRepository;
    public List<Message> getAllChats() {
        return chatRepository.findAll();
    }

}
