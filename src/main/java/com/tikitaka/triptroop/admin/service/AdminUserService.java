package com.tikitaka.triptroop.admin.service;

import com.tikitaka.triptroop.admin.domain.repository.AdminUserRepository;
import com.tikitaka.triptroop.admin.dto.request.AdminUserSaveRequest;
import com.tikitaka.triptroop.admin.dto.response.AdminUserDetailResponse;
import com.tikitaka.triptroop.admin.dto.response.AdminUserResponse;
import com.tikitaka.triptroop.common.exception.BadRequestException;
import com.tikitaka.triptroop.common.exception.NotFoundException;
import com.tikitaka.triptroop.common.exception.type.ExceptionCode;
import com.tikitaka.triptroop.image.util.FileUploadUtils;
import com.tikitaka.triptroop.interest.domain.repository.InterestRepository;
import com.tikitaka.triptroop.interest.domain.repository.UserInterestRepository;
import com.tikitaka.triptroop.user.domain.entity.Profile;
import com.tikitaka.triptroop.user.domain.entity.User;
import com.tikitaka.triptroop.user.domain.repository.ProfileRepository;
import com.tikitaka.triptroop.user.domain.repository.UserRepository;
import com.tikitaka.triptroop.user.service.ProfileService;
import com.tikitaka.triptroop.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

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
    private final UserInterestRepository userInterestRepository;
    private final InterestRepository interestRepository;

    private Pageable getPageable(final Integer page, final String sort) {
        return PageRequest.of(page - 1, 10, Sort.by(sort != null ? sort : "id").descending());
    }

    //    private Pageable getPageable(final Integer page) {
//        return getPageable(page, null);
//    }
    private Pageable getPageable(final Integer page, final Integer pageSize) {
        return PageRequest.of(page - 1, pageSize);
    }

    /* 1. 관리자 회원 관리 - 회원 목록 조회 */
    @Transactional(readOnly = true)
    public Page<AdminUserResponse> findAdminUsers(final Integer page) {
        Pageable pageable = getPageable(page, 10);

        return adminUserRepository.findAdminUsersAll(pageable);
    }

    /* 2. 관리자 회원 관리 - 회원 상세 조회 */
    @Transactional(readOnly = true)
    public AdminUserDetailResponse findAdminUserDetail(final Long userId) {
        return adminUserRepository.findAdminUserDetailByUserId(userId)
                .orElseThrow(() -> new NotFoundException(ExceptionCode.NOT_FOUND_USER));
    }

    /* 3. 관리자 회원 관리 - 회원 등록 */
    @Transactional
    public Long registerAdminUser(final AdminUserSaveRequest adminUserSaveRequest, MultipartFile profileImage) {

        checkForDuplicates(adminUserSaveRequest);

        User user = adminUserSaveRequest.toUser(passwordEncoder);
        user = userRepository.save(user);

        String profileImagePath = null;
        if (profileImage != null && !profileImage.isEmpty()) {
            profileImagePath = FileUploadUtils.uploadFile("", profileImage);
        }

        Profile profile = adminUserSaveRequest.toProfile(user.getId(), imageUrl + profileImagePath);
        profile = profileRepository.save(profile);

        AdminUserSaveRequest saveUser = new AdminUserSaveRequest(user, profile);

        Long userId = user.getId();

        return userId;
    }


    /* 4. 관리자 회원 관리 - 회원 수정 */
//    @Transactional
//    public AdminUserUpdateRequest updateAdminUser(final Long userId, AdminUserUpdateRequest adminUserUpdateRequest, MultipartFile profileImage) {
//
//        User existingUser = userRepository.findById(userId)
//                .orElseThrow(() -> new NotFoundException(ExceptionCode.NOT_FOUND_USER));
//
//        System.out.println("서비스단 existingUser : " + existingUser);
//
//        User updatedUser = adminUserUpdateRequest.updateUser(existingUser, passwordEncoder);
//        System.out.println("서비스단 updateUser : " + updatedUser);
//        userRepository.save(updatedUser);
//
//        Profile existingProfile = profileRepository.findByUserId(userId)
//                .orElseThrow(() -> new NotFoundException(ExceptionCode.NOT_FOUND_USER_PROFILE));
//
//        String profileImagePath = existingProfile.getProfileImage();
//        if (profileImage != null && !profileImage.isEmpty()) {
//            profileImagePath = FileUploadUtils.uploadFile("", profileImage);
//        }
//
//        Profile updatedProfile = adminUserUpdateRequest.updateProfile(existingProfile, profileImagePath);
//        profileRepository.save(updatedProfile);
//
//        updateInterests(userId, adminUserUpdateRequest);
//
//        return adminUserUpdateRequest;
//
//
//    }

    /* 5. 관리자 회원 관리 - 회원 삭제 */
    @Transactional
    public void deleteAdminUser(final Long userId) {

        final User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException(ExceptionCode.NOT_FOUND_USER));

        if (user.isWithdrawnUser()) {
            throw new BadRequestException(ExceptionCode.ALREADY_WITHDRAWN_USER);
        }

        userRepository.deleteById(userId);

    }


    private void checkForDuplicates(AdminUserSaveRequest adminUserSaveRequest) {
        userService.checkEmailDuplicate(adminUserSaveRequest.getEmail());
        profileService.checkNicknameDuplicate(adminUserSaveRequest.getNickname());
    }

//    private void updateInterests(Long userId, AdminUserUpdateRequest adminUserUpdateRequest) {
//        userInterestRepository.deleteByUserId(userId);
//
//        List<UserInterest> newUserInterests = adminUserUpdateRequest.getInterestNames().stream()
//                .map(interestName -> {
//                    Interest interest = interestRepository.findByName(interestName)
//                            .orElseGet(() -> interestRepository.save(Interest.of(interestName)));
//                    return UserInterest.of(userId, interest.getId());
//                })
//                .collect(Collectors.toList());
//
//        userInterestRepository.saveAll(newUserInterests);
//    }

}



