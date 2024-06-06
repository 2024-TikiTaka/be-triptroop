package com.tikitaka.triptroop.admin.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.tikitaka.triptroop.interest.domain.entity.Interest;
import com.tikitaka.triptroop.review.domain.entity.UserReview;
import com.tikitaka.triptroop.user.domain.entity.Profile;
import com.tikitaka.triptroop.user.domain.entity.SocialUser;
import com.tikitaka.triptroop.user.domain.entity.User;
import com.tikitaka.triptroop.user.domain.type.Gender;
import com.tikitaka.triptroop.user.domain.type.Provider;
import com.tikitaka.triptroop.user.domain.type.UserRole;
import com.tikitaka.triptroop.user.domain.type.UserStatus;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@RequiredArgsConstructor
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

    public AdminUserDetailResponse(User user, SocialUser socialUser, List<Interest> interests, UserReview userReview, Profile profile) {
        this.userId = user.getId();
        this.email = user.getEmail();
        this.nickname = profile.getNickname();
        this.role = user.getRole();
        this.createdAt = user.getCreatedAt();
        this.name = user.getName();
        this.gender = user.getGender();
        this.status = user.getStatus();
        this.birth = user.getBirth();
        this.phone = user.getPhone();
        this.reportCount = getReportCount();
        this.provider = socialUser.getProvider();
        this.password = user.getPassword();
        this.godo = user.getGodo();
        this.reviewPoint = userReview.getReviewPoint();
        this.introduction = profile.getIntroduction();
        this.interestName = interests.stream().map(Interest::getName).collect(Collectors.toList());
        this.inquiriesCount = getInquiriesCount();
        this.mbti = profile.getMbti();
        this.contentCount = getContentCount();
        this.friendCount = getFriendCount();
        this.loginCountAndLoginTime = getLoginCountAndLoginTime();
    }

    //    List<ImageResponse> imageResponses, String titleOrNickname
//    public static AdminUserResponse of(User user, SocialUser socialUser, UserReview userReview, Profile profile) {
//
//        return new AdminUserResponse(
//                user.getId(),
//                user.getEmail(),
//                profile.getNickname(),
//                user.getRole(),
//                user.getCreatedAt(),
//                user.getName(),
//                user.getGender(),
//                user.getStatus(),
//                user.getBirth(),
//                user.getPhone(),
//                reportCount,
//                socialUser.getProvider(),
//                user.getPassword(),
//                user.getGodo(),
//                userReview.getReviewPoint(),
//                profile.getIntroduction(),
//                interestName,
//                inquiriesCount,
//                profile.getMbti(),
//                contentCount,
//                friendCount,
//                loginCountAndLoginTime
//        );
//    }

}




