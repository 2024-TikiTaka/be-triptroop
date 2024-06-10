package com.tikitaka.triptroop.admin.service;

import com.tikitaka.triptroop.admin.domain.repository.AdminUserRepository;
import com.tikitaka.triptroop.admin.dto.request.AdminUserSaveRequest;
import com.tikitaka.triptroop.admin.dto.response.AdminUserDetailResponse;
import com.tikitaka.triptroop.admin.dto.response.AdminUserResponse;
import com.tikitaka.triptroop.admin.dto.response.AdminUserSaveResponse;
import com.tikitaka.triptroop.common.exception.NotFoundException;
import com.tikitaka.triptroop.common.exception.type.ExceptionCode;
import com.tikitaka.triptroop.image.util.FileUploadUtils;
import com.tikitaka.triptroop.user.domain.entity.Profile;
import com.tikitaka.triptroop.user.domain.entity.User;
import com.tikitaka.triptroop.user.domain.repository.ProfileRepository;
import com.tikitaka.triptroop.user.domain.repository.UserRepository;
import com.tikitaka.triptroop.user.service.ProfileService;
import com.tikitaka.triptroop.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class AdminUserService {

    @Value("${image.image-url}")
    private String imageUrl;

    private final AdminUserRepository adminUserRepository;
    private final UserRepository userRepository;
    private final ProfileRepository profileRepository;
    private final UserService userService;
    private final ProfileService profileService;
    private final PasswordEncoder passwordEncoder;

    /* 1. 관리자 회원 관리 - 회원 목록 조회 */
    @Transactional(readOnly = true)
    public List<AdminUserResponse> findAdminUsers() {
        return adminUserRepository.findAdminUsersAll();
    }

    /* 2. 관리자 회원 관리 - 회원 상세 조회 */
    @Transactional(readOnly = true)
    public AdminUserDetailResponse findAdminUserDetail(final Long userId) {
        return adminUserRepository.findAdminUserDetailByUserId(userId)
                .orElseThrow(() -> new NotFoundException(ExceptionCode.NOT_FOUND_USER));
    }

    /* 3. 관리자 회원 관리 - 회원 등록 */
    @Transactional
    public AdminUserSaveResponse registerAdminUser(final AdminUserSaveRequest adminUserSaveRequest, MultipartFile profileImage) {

        // 이메일 중복 체크
        userService.checkEmailDuplicate(adminUserSaveRequest.getEmail());

        // 닉네임 중복 체크
        profileService.checkNicknameDuplicate(adminUserSaveRequest.getNickname());

        // Null 체크
        if (adminUserSaveRequest.getGender() == null) {
            throw new IllegalArgumentException("Gender is required.");
        }

        // User 엔티티 생성 및 저장
        final User newUser = User.from(
                adminUserSaveRequest.getEmail(),
                passwordEncoder.encode(adminUserSaveRequest.getPassword()),
                adminUserSaveRequest.getName(),
                adminUserSaveRequest.getPhone(),
                adminUserSaveRequest.getGender(),
                adminUserSaveRequest.getRole(),
                adminUserSaveRequest.getStatus(),
                adminUserSaveRequest.getBirth()
        );

        final User user = userRepository.save(newUser);

        String profileImagePath = null;
        if (profileImage != null && !profileImage.isEmpty()) {
            profileImagePath = FileUploadUtils.uploadFile("", profileImage);
        }


        // Profile 엔티티 생성 및 저장
        final Profile newProfile = Profile.of(
                user.getId(),
                adminUserSaveRequest.getNickname(),
                imageUrl + profileImagePath,
                adminUserSaveRequest.getIntroduction(),
                adminUserSaveRequest.getMbti()
        );

        profileRepository.save(newProfile);

        return new AdminUserSaveResponse(user, newProfile);
    }


}
