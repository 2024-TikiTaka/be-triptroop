//package com.tikitaka.triptroop.admin.dto.request;
//
//import com.fasterxml.jackson.annotation.JsonInclude;
//import com.tikitaka.triptroop.user.domain.entity.Profile;
//import com.tikitaka.triptroop.user.domain.entity.User;
//import com.tikitaka.triptroop.user.domain.type.UserRole;
//import lombok.Getter;
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//import java.util.List;
//
//@Getter
//@RequiredArgsConstructor
//@JsonInclude(JsonInclude.Include.NON_NULL)
//public class AdminUserUpdateRequest {
//    private String phone; //핸드폰
//    private String password;//비밀번호
//    private UserRole role; //권한
//    private List<String> interestNames; //여행취향
//
//    public static AdminUserUpdateRequest from(String phone, String password, UserRole role, String introduction, String mbti, String interestNames) {
//        return new AdminUserUpdateRequest(phone, password, role, introduction, mbti, interestNames);
//    }
//
//    public AdminUserUpdateRequest(String phone, String password, UserRole role, String introduction, String mbti, String interestNames) {
//        this.phone = phone;
//        this.password = password;
//        this.role = role;
//        this.introduction = introduction;
//        this.mbti = mbti;
//        this.interestNames = interestNames != null ? List.of(interestNames.split(",")) : null;
//    }
//
//    public User updateUser(User user, PasswordEncoder passwordEncoder) {
//        String updatedPassword = password != null ? passwordEncoder.encode(password) : user.getPassword();
//
//        return User.from(
//                phone != null ? phone : user.getPhone(),
//                updatedPassword,
//                role != null ? role : user.getRole()
//        );
//    }
//
//    public Profile updateProfile(Profile profile, String profileImagePath) {
//        return Profile.of(
//                profile.getUserId(),
//                profile.getNickname(),
//                profileImagePath != null ? profileImagePath : profile.getProfileImage(),
//                introduction != null ? introduction : profile.getIntroduction(),
//                mbti != null ? mbti : profile.getMbti()
//        );
//    }
//}
