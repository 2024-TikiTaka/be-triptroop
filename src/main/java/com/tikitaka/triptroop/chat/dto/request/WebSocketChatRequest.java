package com.tikitaka.triptroop.chat.dto.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@RequiredArgsConstructor
public class WebSocketChatRequest {

    private Long id;
    private Long roomId;
    private MessageType type;
    private String content;
    private String sender;
    private LocalDateTime writtenAt;

    public enum MessageType {
        CHAT,
        JOIN,
        LEAVE
    }

}