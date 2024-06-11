package com.tikitaka.triptroop.chat.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
public class PrivateChatRoomCreateRequest {

    @NotBlank
    private String roomName;

    @NotBlank
    private String type;

    @NotBlank
    private Long member;

    private String content;

    private Long sender;

    private LocalDateTime writtenAt;

    public PrivateChatRoomCreateRequest(String roomName, String type, Long member) {
        this.roomName = roomName;
        this.type = type;
        this.member = member;
    }

}
