package com.tikitaka.triptroop.admin.dto.request;

import com.tikitaka.triptroop.common.exception.NotFoundException;
import com.tikitaka.triptroop.common.exception.type.ExceptionCode;
import com.tikitaka.triptroop.user.domain.entity.Profile;
import com.tikitaka.triptroop.user.domain.entity.User;
import com.tikitaka.triptroop.user.domain.type.Gender;
import com.tikitaka.triptroop.user.domain.type.UserRole;
import com.tikitaka.triptroop.user.domain.type.UserStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;

@Getter
@RequiredArgsConstructor
public class AdminUserSaveRequest {

    private Long userId;

    @NotBlank(message = "이메일은 필수 항목입니다.")
    private String email;

    @NotBlank(message = "비밀번호는 필수 항목입니다.")
    @Size(min = 8, max = 20, message = "8-20자 이내로 입력해주세요.")
    private String password;

    @NotBlank(message = "비밀번호 확인은 필수 항목입니다.")
    @Size(min = 8, max = 20, message = "8-20자 이내로 입력해주세요.")
    private String confirmPassword;

    @NotBlank(message = "이름은 필수 항목입니다.")
    @Pattern(regexp = "^[가-힣]{2,}$", message = "이름의 형식이 올바르지 않습니다.")
    private String name;

    @NotBlank
    @Pattern(regexp = "^01(?:0|1|[6-9])[.-]?(\\d{3}|\\d{4})[.-]?(\\d{4})$", message = "올바른 전화번호 형식을 입력해주세요.")
    private String phone;

    @NotBlank(message = "성별은 필수 항목입니다.")
    @Pattern(regexp = "M|F", message = "성별의 형식이 올바르지 않습니다. (ex. 여자-F/남자-M)")
    private Gender gender;
    private UserRole role;
    private UserStatus status;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotBlank(message = "생년월일은 필수 항목입니다.")
    @Pattern(regexp = "\\d{8}", message = "생년월일의 형식이 올바르지 않습니다. (ex. 19900101)")
    private LocalDate birth;
    private String nickname;
    private String profileImage;
    private String introduction;
    private String mbti;

    private AdminUserSaveRequest(String email, String password, String confirmPassword, String name, String phone, Gender gender, UserRole role, UserStatus status, LocalDate birth, String nickname, String profileImage, String introduction, String mbti) {
        this.email = email;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.name = name;
        this.phone = phone;
        this.gender = gender;
        this.role = role;
        this.status = status;
        this.birth = birth;
        this.nickname = nickname;
        this.profileImage = profileImage;
        this.introduction = introduction;
        this.mbti = mbti;
    }

    public AdminUserSaveRequest(User user, Profile profile) {
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.confirmPassword = user.getPassword();
        this.name = user.getName();
        this.phone = user.getPhone();
        this.gender = user.getGender();
        this.role = user.getRole();
        this.status = user.getStatus();
        this.birth = user.getBirth();
        this.nickname = profile.getNickname();
        this.profileImage = profile.getProfileImage();
        this.introduction = profile.getIntroduction();
        this.mbti = profile.getMbti();
    }

    public static AdminUserSaveRequest from(String email, String password, String confirmPassword, String name, String phone, Gender gender, UserRole role, UserStatus status, LocalDate birth, String nickname, String profileImage, String introduction, String mbti) {
        // 유효성 검사 로직 추가
        validateEmail(email);
        validatePassword(password, confirmPassword);

        return new AdminUserSaveRequest(email, password, confirmPassword, name, phone, gender, role, status, birth, nickname, profileImage, introduction, mbti);
    }

    private static void validateEmail(String email) {
        // 이메일 유효성 검사 로직
        if (email == null || email.isEmpty()) {
            throw new NotFoundException(ExceptionCode.INVALID_EMAIL);
        }
    }

    private static void validatePassword(String password, String confirmPassword) {
        // 비밀번호 유효성 검사 로직
        if (password == null || password.isEmpty()) {
            throw new NotFoundException(ExceptionCode.INVALID_PASSWORD);
        }
        if (!password.equals(confirmPassword)) {
            throw new NotFoundException(ExceptionCode.INVALID_PASSWORD);
        }
    }


    public User toUser(PasswordEncoder passwordEncoder) {
        return User.from(
                email,
                passwordEncoder.encode(password),
                name,
                phone,
                gender,
                role,
                status,
                birth
        );
    }

    public Profile toProfile(Long userId, String profileImagePath) {
        return Profile.of(
                userId,
                nickname,
                profileImagePath,
                introduction,
                mbti
        );
    }


}
