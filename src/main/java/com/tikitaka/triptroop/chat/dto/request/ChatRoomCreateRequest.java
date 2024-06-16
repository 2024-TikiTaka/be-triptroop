package com.tikitaka.triptroop.chat.dto.request;

import com.tikitaka.triptroop.chat.domain.type.ChatRoomType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

import static com.tikitaka.triptroop.chat.domain.type.ChatRoomType.PRIVATE;

@Getter
@RequiredArgsConstructor
public class ChatRoomCreateRequest {

    @NotBlank
    private String roomName;

    @NotNull
    private Long friendId; // 채팅을 하려는 친구의 ID

    private ChatRoomType type =  PRIVATE; // 채팅방 타입 기본값은 PRIVATE

    public ChatRoomCreateRequest(String roomName, Long friendId, ChatRoomType type) {
        this.roomName = roomName;
        this.friendId = friendId;
        this.type = type;
    }

}
