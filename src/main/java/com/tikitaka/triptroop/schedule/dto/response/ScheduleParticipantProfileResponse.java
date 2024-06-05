package com.tikitaka.triptroop.schedule.dto.response;

import com.querydsl.core.annotations.QueryProjection;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class ScheduleParticipantProfileResponse {

    private Long scheduleParticipantId;
    private Long userId;
    private String nickname;
    private String mbti;
    private String reviewContent;
    private Double reviewPoint;
    private String profileImage;

    @QueryProjection
    public ScheduleParticipantProfileResponse(Long scheduleParticipantId, Long userId, String nickname, String profileImage, String mbti, String reviewContent, Double reviewPoint) {
        this.scheduleParticipantId = scheduleParticipantId;
        this.userId = userId;
        this.nickname = nickname.toString();
        this.profileImage = profileImage;
        this.mbti = mbti.toString();
        this.reviewContent = reviewContent;
        this.reviewPoint = reviewPoint;
    }
}
