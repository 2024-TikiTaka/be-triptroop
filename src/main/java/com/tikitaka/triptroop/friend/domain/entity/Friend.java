package com.tikitaka.triptroop.friend.domain.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "friends")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class Friend {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "friend_id")
    private Long id;

    @Column(name = "requester_id", nullable = false)
    private Long requesterId;

    @Column(name = "accepter_id", nullable = false)
    private Long accepterId;
  
    @Column(name = "status", nullable = false)
    private String status;

    private Friend(Long requesterId, Long accepterId, String status) {
        this.requesterId = requesterId;
        this.accepterId = accepterId;
        this.status = status;
    }

    public static Friend of(Long requesterId, Long accepterId) {
        return new Friend(requesterId, accepterId, "REQUESTED");
    }
}
