package com.tikitaka.triptroop.chat.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
public class PrivateChatRoomCreateRequest {

    @NotBlank
    private String roomName;

    @NotBlank
    private String type;

    @NotBlank
    private String member;

    private String content;

    private String sender;

    private LocalDateTime writtenAt;

    public PrivateChatRoomCreateRequest(String roomName, String type, String member) {
        this.roomName = roomName;
        this.type = type;
        this.member = member;
    }

}
