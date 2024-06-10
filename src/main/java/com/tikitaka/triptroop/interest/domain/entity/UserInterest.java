package com.tikitaka.triptroop.interest.domain.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user_interests")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserInterest {

    @Id
    @Column(name = "user_interest_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    private Long interestId;

    private UserInterest(Long userId, Long interestId) {
        this.userId = userId;
        this.interestId = interestId;
    }

    public static UserInterest of(Long userId, Long interestId) {
        return new UserInterest(userId, interestId);
    }
}
