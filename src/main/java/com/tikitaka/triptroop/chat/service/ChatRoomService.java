package com.tikitaka.triptroop.chat.service;

import com.tikitaka.triptroop.chat.domain.entity.ChatRoom;
import com.tikitaka.triptroop.chat.domain.repository.ChatRoomRepository;
import com.tikitaka.triptroop.chat.dto.request.PrivateChatRoomCreateRequest;
import com.tikitaka.triptroop.chat.dto.response.ChatResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ChatRoomService {
    private final ChatRoomRepository chatRoomRepository;
    public List<ChatResponse> getUserRooms(Long userId) {
        List<ChatRoom> chatRoom = chatRoomRepository.findByMemberContaining(userId);
        return chatRoom.stream()
                .map(ChatResponse::from)
                .collect(Collectors.toList());
    }

    @Transactional
    public ChatResponse createChatRoom(PrivateChatRoomCreateRequest request, @AuthenticationPrincipal UserDetails userDetails) {
        ChatRoom chatRoom = ChatRoom.of(
                request.getRoomName(),
                request.getType(),
                Long.valueOf(userDetails.getUsername()), // 로그인 한 사용자 정보 가져오기
                request.getMember()
        );

        chatRoom = chatRoomRepository.save(chatRoom);
        return ChatResponse.from(chatRoom);
    }

//    public ChatResponse createChatRoom(@Valid ChatRequest request) {
//        ChatRoom chatRoom = chatRoomRepository.createChatRoom(request);
//        return ChatResponse.from(chatRoom);
//    }
}
