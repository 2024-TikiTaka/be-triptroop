package com.tikitaka.triptroop.admin.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.tikitaka.triptroop.interest.domain.entity.Interest;
import com.tikitaka.triptroop.interest.domain.entity.UserInterest;
import com.tikitaka.triptroop.review.domain.entity.UserReview;
import com.tikitaka.triptroop.user.domain.entity.Profile;
import com.tikitaka.triptroop.user.domain.entity.SocialUser;
import com.tikitaka.triptroop.user.domain.entity.User;
import com.tikitaka.triptroop.user.domain.type.Gender;
import com.tikitaka.triptroop.user.domain.type.Provider;
import com.tikitaka.triptroop.user.domain.type.UserRole;
import com.tikitaka.triptroop.user.domain.type.UserStatus;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AdminUserDetailResponse {

    private final Long userId;
    private final String email;
    private final String nickname;
    private final UserRole role;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private final LocalDateTime createdAt;
    private final String name;
    private final Gender gender;
    private final UserStatus status;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private final LocalDate birth;
    private final String phone;
    private final Integer reportCount;
    private final Provider provider;
    private final String password;
    private final Integer godo;
    private final Integer reviewPoint;
    private final String introduction;
    private final List<String> interestName;
    private final Integer inquiriesCount;
    private final String mbti;
    private final Integer contentCount;
    private final Integer friendCount;
    private final String loginCountAndLoginTime;

    public AdminUserDetailResponse(User user, SocialUser socialUser, UserInterest userInterest, Interest interest, UserReview userReview, Profile profile) {
        this.userId = user.getId();
        this.email = user.getEmail();
        this.nickname = profile != null ? profile.getNickname() : null;
        this.role = user.getRole();
        this.createdAt = user.getCreatedAt();
        this.name = user.getName();
        this.gender = user.getGender();
        this.status = user.getStatus();
        this.birth = user.getBirth();
        this.phone = user.getPhone();
        this.reportCount = getReportCount();
        this.provider = socialUser != null ? socialUser.getProvider() : null;
        this.password = user.getPassword();
        this.godo = user.getGodo();
        this.reviewPoint = userReview != null ? userReview.getReviewPoint() : null;
        this.introduction = profile != null ? profile.getIntroduction() : null;
        this.interestName = userInterest != null && interest != null ? List.of(interest.getName()) : null;
        this.inquiriesCount = getInquiriesCount();
        this.mbti = profile != null ? profile.getMbti() : null;
        this.contentCount = getContentCount();
        this.friendCount = getFriendCount();
        this.loginCountAndLoginTime = getLoginCountAndLoginTime();
    }


}




