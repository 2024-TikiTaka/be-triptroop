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
import java.util.Collections;
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

    // 정적 팩토리 메서드
    public static ChatRoom of(String roomName, String type, String creator, String member) {
        return new ChatRoom(
                LocalDateTime.now(),
                roomName,
                ChatRoomType.valueOf(type),
                creator,
                Collections.singletonList(member),
                null // 초기 lastMessageAt 값은 null
        );
    }

    // 생성자
    private ChatRoom(LocalDateTime createdAt, String roomName, ChatRoomType type, String creator, List<String> member, LocalDateTime lastMessageAt) {
        this.createdAt = createdAt;
        this.roomName = roomName;
        this.type = type;
        this.creator = creator;
        this.member = member;
        this.lastMessageAt = lastMessageAt;
    }

}
