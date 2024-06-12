package com.tikitaka.triptroop.schedule.dto.response;

import com.querydsl.core.annotations.QueryProjection;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class ScheduleInformationResponse {

    private Long scheduleId;
    private String scheduleImage;// path + uuid
    private Long userId;
    private LocalDate startDate;
    private LocalDate endDate;
    private String title;
    private Integer count;
    private Integer views;
    private String nickname;
    private String mbti;
    private String profileImage;


    @QueryProjection
    public ScheduleInformationResponse(Long scheduleId, String path, String uuid, Long userId, LocalDate startDate, LocalDate endDate, String title, Integer count, Integer views, String nickname, String mbti, String profileImage) {
        this.scheduleId = scheduleId;
        this.scheduleImage = path + uuid;
        this.userId = userId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.title = title;
        this.count = count;
        this.views = views;
        this.nickname = nickname;
        this.mbti = mbti;
        this.profileImage = profileImage;


    }
}
