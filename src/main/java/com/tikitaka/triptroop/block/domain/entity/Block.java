package com.tikitaka.triptroop.block.domain.entity;

import com.tikitaka.triptroop.block.domain.type.BlockStatus;
import com.tikitaka.triptroop.user.domain.entity.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Entity
@Table(name = "blocks")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Block {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "blocked_id")
    private User blocked;

    @ManyToOne
    @JoinColumn(name = "blocker_id")
    private User blocker;

    @Enumerated(value = EnumType.STRING)
    private BlockStatus status;

    @CreatedDate
    private LocalDateTime blockedAt;

    @LastModifiedDate
    private LocalDateTime modifiedAt;

    private LocalDateTime unblockedAt;

}
