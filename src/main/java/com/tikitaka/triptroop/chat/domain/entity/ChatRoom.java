package com.tikitaka.triptroop.chat.domain.entity;

import jakarta.persistence.Id;
import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;
import java.util.List;

@Document(collection = "chat_room")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class ChatRoom {

    @Id
    private ObjectId id;

    @Field("room_name")
    private String roomName;

    private String invitor;

    private List<String> participants;

    @CreatedDate
    @Field("created_at")
    private LocalDateTime createdAt;

}
