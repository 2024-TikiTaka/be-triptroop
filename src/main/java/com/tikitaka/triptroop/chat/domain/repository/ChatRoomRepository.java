package com.tikitaka.triptroop.chat.domain.repository;

import com.tikitaka.triptroop.chat.domain.entity.ChatRoom;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ChatRoomRepository extends MongoRepository<ChatRoom, ObjectId> {
    List<ChatRoom> findByMemberContaining(Long username);


}
