package com.tikitaka.triptroop.travel.domain.entity;

import com.tikitaka.triptroop.common.domain.BaseTimeEntity;
import com.tikitaka.triptroop.user.domain.entity.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@Entity
@Table(name = "travel_comments")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class TravelComment extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "travel_comment_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User userId;
    //
//    @ManyToOne
//    @JoinColumn(name = "travel_id")
    private Long travelId;

    private String content;

    private boolean isDeleted;

    private LocalDateTime deletedAt;

    public TravelComment(Long travelId, User user, String content) {
        this.travelId = travelId;
        this.userId = user;
        this.content = content;
        this.isDeleted = false;
    }


    public static TravelComment of(final Long travelId, final User userId, final String content) {

        return new TravelComment(
                travelId,
                userId,
                content
        );
    }
}
