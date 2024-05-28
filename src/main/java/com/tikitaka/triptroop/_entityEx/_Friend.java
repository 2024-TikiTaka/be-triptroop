package com.tikitaka.triptroop._entityEx;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "friends")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class _Friend {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "requester_id", nullable = false)
    private _User requester;

    @ManyToOne
    @JoinColumn(name = "accepter_id", nullable = false)
    private _User accepter;

    @Column(nullable = false, columnDefinition = "VARCHAR(10) DEFAULT 'REQUESTED'")
    private String status;
}
