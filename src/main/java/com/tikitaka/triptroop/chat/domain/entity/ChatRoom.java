package com.tikitaka.triptroop.chat.domain.entity;

import com.tikitaka.triptroop.chat.domain.type.ChatRoomType;
import jakarta.persistence.Id;
import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

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

    private String creator;

    private List<String> member;

    @Field(targetType = FieldType.STRING)
    private ChatRoomType type;

    @CreatedDate
    @Field("created_at")
    private LocalDateTime createdAt;

    private String url;

    private LocalDateTime lastMessageAt;

}
