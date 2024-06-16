package com.tikitaka.triptroop.chat.controller;

import com.tikitaka.triptroop.chat.dto.request.WebSocketChatRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

/* 웹 소켓 이벤트 처리 클래스 */
@Component
@Slf4j
public class WebSocketEventListener {
    private final SimpMessageSendingOperations messagingTemplate;

    @Autowired
    public WebSocketEventListener(SimpMessageSendingOperations messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @EventListener
    /* 사용자가 웹소켓에 연결되면 로그를 남긴다. */
    public void handleWebSocketConnectListener(SessionConnectedEvent event) {
        log.info("Received a new web socket connection");
    }

    @EventListener
    public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
        /* 사용자의 웹소켓 연결이 끊어지면 사용자의 정보를 받아 방에서 나갔다는 메시지를 전송한다. */
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());

        String username = (String) headerAccessor.getSessionAttributes().get("username");
        if (username != null) {
            log.info("User Disconnected : " + username);

            WebSocketChatRequest message = new WebSocketChatRequest();
//            message.setType(Message.MessageType.LEAVE);
            message.setSender(Long.valueOf(username));

            messagingTemplate.convertAndSend("/topic/public", message);

        }
    }
}

















