package com.tikitaka.triptroop.chat.service;

import com.tikitaka.triptroop.chat.domain.entity.Message;
import com.tikitaka.triptroop.chat.domain.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageService {
    private final MessageRepository messageRepository;
    public List<Message> getAllMessages() {
        return messageRepository.findAll();
    }
}
