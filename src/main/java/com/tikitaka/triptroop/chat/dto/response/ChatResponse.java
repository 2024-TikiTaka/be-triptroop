package com.tikitaka.triptroop.chat.dto.response;

import com.tikitaka.triptroop.chat.domain.entity.ChatRoom;
import lombok.*;

import java.util.List;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class ChatResponse {
    private final String id;

    private final String roomName;

    private final Long creator;

    private List<Long> member;

    private String type;

    private String createdAt;

    private String url;

    private String lastMessageAt;

    public static ChatResponse from(final ChatRoom chatRoom) {
        return new ChatResponse(
                chatRoom.getId().toHexString(),
                chatRoom.getRoomName(),
                chatRoom.getCreator()
        );
    }
}
