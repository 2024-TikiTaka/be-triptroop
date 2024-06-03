package com.tikitaka.triptroop.chat.domain.repository;

import com.tikitaka.triptroop.chat.domain.entity.Message;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MessageRepository extends MongoRepository<Message, ObjectId> {


}
