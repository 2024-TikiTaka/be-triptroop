package com.tikitaka.triptroop.chat.controller;

import com.tikitaka.triptroop.chat.domain.entity.Message;
import com.tikitaka.triptroop.chat.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/vi/chat")
@RequiredArgsConstructor
public class ChatController {

    private final ChatService chatService;

    @GetMapping("/list")
    public List<Message> getChatList() {
        return chatService.getAllChats();
    }

}
