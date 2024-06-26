package com.tikitaka.triptroop.chat.controller;

import com.tikitaka.triptroop.chat.dto.request.WebSocketChatRequest;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

@Controller
public class WebSocketController {
    /* 클라이언트가 '/chat.sendMessage' 주소로 메시지를 전송하면,
    * 서버에서 메시지를 받아서 '/topic/public' 주제를 구독하는(채팅방에 참여하고 있는) 클라이언트에게 메시지를 브로드캐스팅한다. */
    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/public")
    public WebSocketChatRequest sendMessage(@Payload WebSocketChatRequest message) {
        /* 클라이언트로부터 수신한 메시지를 처리하는 메서드
        * @Payload 어노테이션은 메시지의 페이로드를 메서드 매개변수로 전달한다.
        * 즉, Message 객체를 매개변수로 받고 어떠한 가공도 하지 않은 채로 클라이언트에게 반환한다. */
        return message;
    }

    @MessageMapping("/chat.adduser")
    @SendTo("/topic/public")
    public WebSocketChatRequest addUser(@Payload WebSocketChatRequest message,
                                        SimpMessageHeaderAccessor headerAccessor) {
        /* 클라이언트가 '/chat.addUser' 주소로 메시지를 보내면,
        * 서버에서 해당 메시지를 받아서 새로운 사용자의 정보를 추출하고,
        * 웹 소켓 세션에 사용자의 이름을 추가한 뒤,
        * 클라이언트에게 해당 메시지를 전달하여 새로운 사용자의 참여를 알림. */
        headerAccessor.getSessionAttributes().put("username", message.getSender());
        return message;

    }

}

