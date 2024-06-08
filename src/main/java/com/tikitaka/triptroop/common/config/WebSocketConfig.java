package com.tikitaka.triptroop.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
    /* 클라이언트와 서버 간의 웹 소켓 연결 설정.
    * 클라이언트가 웹 소켓을 통해 연결하려는 엔드포인트 등록.
    * '/ws'를 웹 소켓으로 등록하고, SockJs를 사용하여 클라이언트와 연결.*/
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws")
                .setAllowedOrigins("http://localhost:3000")  // CORS 허용 설정
                .withSockJS();
    }

    /* 메시지 브로커를 설정한다.
    * 메시지 브로커는 클라이언트와 서버 간의 메시지를 전달하는 중간 매개체이다.
    * 브로커는 메시지를 수신하고 저장한 다음 필요한 대상에게 전달한다. */
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        /* 클라이언트에게 메시지를 보낼 때 메시지의 목적지를 설정한다.
        * 클라이언트에서 '/app'으로 시작하는 목적지로 메시지를 보내면
        * 특정 컨트롤러로 메시지를 라우팅하도록 지정한다. */
        registry.setApplicationDestinationPrefixes("/app");
        /* 클라이언트가 '/topic/으로 시작하는 주제를 구독하면,
        * 해당 주제에 관련된 메시지를 받겠다는 의미이다. */
        registry.enableSimpleBroker("/topic");
    }
}
