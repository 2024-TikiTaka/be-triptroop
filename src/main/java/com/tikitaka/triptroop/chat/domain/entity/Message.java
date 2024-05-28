package com.tikitaka.triptroop.chat.domain.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

//@jakarta.persistence.Entity
//@Table(name = "") // <- ""안에 엔티티 매핑할 테이블 명 적어주세요.
@Getter
@Setter
//@NoArgsConstructor(access = AccessLevel.PROTECTED)
//@EntityListeners(AuditingEntityListener.class)
public class Message { // <- Entity 를 본인의 엔티티 명으로 바꿔 주세요. ( ! 첫문자 대문자임 (ex-Member O / member X) )

    /* 매핑할 필드( 컬럼명 : 카멜케이스 )를 적어주세요. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private MessageType type;
    private String content;
    private String sender;

    public enum MessageType {
        CHAT,
        JOIN,
        LEAVE
    }


}
