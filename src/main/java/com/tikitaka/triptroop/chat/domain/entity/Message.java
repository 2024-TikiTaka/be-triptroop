package com.tikitaka.triptroop.chat.domain.entity;

import jakarta.persistence.*;
import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;

@Document(collection = "message")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class Message {

    @Id
    private ObjectId id;

    @Field("room_id")
    private int roomId;

    private Long sender;

    private String content;

    @CreatedDate
    @Field("written_at")
    private LocalDateTime writtenAt;

}

