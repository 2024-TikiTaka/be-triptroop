package com.tikitaka.triptroop.chat.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
public class ChatResponse {
    private String id;

    private String roomName;

    private String invitor;

    private List<String> participants;

    private String createdAt;


}
