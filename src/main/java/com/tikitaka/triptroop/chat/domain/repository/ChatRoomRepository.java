package com.tikitaka.triptroop.chat.domain.repository;

import com.tikitaka.triptroop.chat.domain.entity.ChatRoom;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ChatRoomRepository extends MongoRepository<ChatRoom, ObjectId> {


//    ChatRoom createChatRoom(@Valid ChatRequest request);
}
