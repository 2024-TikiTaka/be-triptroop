package com.tikitaka.triptroop.chat.service;

import com.tikitaka.triptroop.chat.domain.entity.ChatRoom;
import com.tikitaka.triptroop.chat.domain.repository.ChatRoomRepository;
import com.tikitaka.triptroop.chat.dto.request.ChatRoomCreateRequest;
import com.tikitaka.triptroop.chat.dto.response.ChatResponse;
import com.tikitaka.triptroop.user.domain.type.CustomUser;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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
    public ChatResponse createChatRoom(ChatRoomCreateRequest request, @AuthenticationPrincipal CustomUser loginUser) {
        Long userId = Long.valueOf(loginUser.getUserId());
        Long friendId = request.getFriendId();

        // 채팅방 URL 생성
        String url = "ws://localhost:8080/ws/" + userId + "_" + friendId;

        ChatRoom chatRoom = ChatRoom.of(
                request.getRoomName(),
                request.getType(),
                userId,
                request.getFriendId(),
                url,
                LocalDateTime.now()
        );

        chatRoom = chatRoomRepository.save(chatRoom);
        return ChatResponse.from(chatRoom);
    }


}
