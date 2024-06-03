package com.tikitaka.triptroop.travel.domain.entity;

import com.tikitaka.triptroop.common.domain.BaseTimeEntity;
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

//    @ManyToOne
//    @JoinColumn(name = "user_id")
//    private User userId;

    private Long userId;
    //
//    @ManyToOne
//    @JoinColumn(name = "travel_id")
    private Long travelId;

    private String content;

    private boolean isDeleted;

    private LocalDateTime deletedAt;

    public TravelComment(Long travelId, Long userId, String content) {
        this.travelId = travelId;
        this.userId = userId;
        this.content = content;
        this.isDeleted = false;
    }


    public static TravelComment of(final Long travelId, final Long userId, final String content) {

        return new TravelComment(
                travelId,
                userId,
                content
        );
    }

    public void update(Long travelId, Long userId, String content) {

        this.travelId = travelId;
        this.userId = userId;
        this.content = content;
    }
}
