package com.tikitaka.triptroop.admin.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
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
    private final List<String> interestNames;
    private final Integer inquiriesCount;
    private final String mbti;
    private final Integer contentCount;
    private final Integer friendCount;
//    private final String loginCountAndLoginTime;

    public AdminUserDetailResponse(User user, Profile profile, SocialUser socialUser,
                                   long reviewPoint, long inquiriesCount, long contentCount, long friendCount,
            /*String loginCountAndLoginTime,*/ String interestName) {
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
        this.reportCount = 0;  // 예제에서는 reportCount가 어디에서 오는지 명확하지 않으므로 기본값 0으로 설정
        this.provider = socialUser != null ? socialUser.getProvider() : null;
        this.password = user.getPassword();
        this.godo = user.getGodo();
        this.reviewPoint = (int) reviewPoint;
        this.introduction = profile != null ? profile.getIntroduction() : null;
        this.interestNames = interestName != null ? List.of(interestName) : null;
        this.inquiriesCount = (int) inquiriesCount;
        this.mbti = profile != null ? profile.getMbti() : null;
        this.contentCount = (int) contentCount;
        this.friendCount = (int) friendCount;
//        this.loginCountAndLoginTime = loginCountAndLoginTime;
    }
}
