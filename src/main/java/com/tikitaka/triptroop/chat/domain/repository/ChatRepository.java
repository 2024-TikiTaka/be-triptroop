package com.tikitaka.triptroop.chat.domain.repository;

import com.tikitaka.triptroop.chat.domain.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRepository extends JpaRepository<Message, Long> {

    /* Repository 앞에 부분을 지우고 본인의 기능 폴더명을 적어주세요. */

}
