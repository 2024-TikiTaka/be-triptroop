package com.tikitaka.triptroop.chat.controller;

import com.tikitaka.triptroop.chat.domain.entity.Message;
import com.tikitaka.triptroop.chat.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/chat")
@RequiredArgsConstructor
public class MessageController {

    private final MessageService messageService;

    @GetMapping("/message")
    public List<Message> getChatMessage() {
        return messageService.getAllMessages();
    }

}
