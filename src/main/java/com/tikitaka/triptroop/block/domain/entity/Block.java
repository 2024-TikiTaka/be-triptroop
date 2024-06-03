package com.tikitaka.triptroop.block.domain.entity;

import com.tikitaka.triptroop.block.domain.type.BlockStatus;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name = "blocks")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class Block {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "block_id")
    private Long id;

    //    @ManyToOne
//    @JoinColumn(name = "blocked_id")
//    private User blocked;
    private Long blockedId;

    //    @ManyToOne
//    @JoinColumn(name = "blocker_id")
//    private User blocker;
    private Long blockerId;

    @Enumerated(value = EnumType.STRING)
    private BlockStatus status;

    @CreatedDate
    private LocalDateTime blockedAt;

    @LastModifiedDate
    private LocalDateTime modifiedAt;

    private LocalDateTime unblockedAt;

}
