package com.tikitaka.triptroop.admin.service;

import com.tikitaka.triptroop.admin.domain.repository.AdminUserRepository;
import com.tikitaka.triptroop.admin.dto.request.AdminUserSaveRequest;
import com.tikitaka.triptroop.admin.dto.response.AdminUserDetailResponse;
import com.tikitaka.triptroop.admin.dto.response.AdminUserResponse;
import com.tikitaka.triptroop.common.exception.BadRequestException;
import com.tikitaka.triptroop.common.exception.NotFoundException;
import com.tikitaka.triptroop.common.exception.type.ExceptionCode;
import com.tikitaka.triptroop.image.util.FileUploadUtils;
import com.tikitaka.triptroop.user.domain.entity.Profile;
import com.tikitaka.triptroop.user.domain.entity.User;
import com.tikitaka.triptroop.user.domain.repository.ProfileRepository;
import com.tikitaka.triptroop.user.domain.repository.UserRepository;
import com.tikitaka.triptroop.user.service.ProfileService;
import com.tikitaka.triptroop.user.service.UserService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
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

    @PersistenceContext
    private final EntityManager entityManager;

    /* 1. 관리자 회원 관리 - 회원 목록 조회 */
    @Transactional(readOnly = true)
    public Page<AdminUserResponse> findAdminUsers(final Integer page, final String type, final String keyword, final String sort) {
        String sortBy = (sort == null || sort.isEmpty()) ? "id" : sort;
        Pageable pageable = getPageable(page, sortBy);

        boolean hasKeyword = keyword != null && !keyword.isEmpty();
        String jpql = buildJpqlQuery(type, hasKeyword, sortBy);
        String countJpql = buildCountJpqlQuery(type, hasKeyword);

        Query query = entityManager.createQuery(jpql, AdminUserResponse.class);
        Query countQuery = entityManager.createQuery(countJpql);

        if (hasKeyword) {
            query.setParameter("keyword", "%" + keyword + "%");
            countQuery.setParameter("keyword", "%" + keyword + "%");
        }

        query.setFirstResult((int) pageable.getOffset());
        query.setMaxResults(pageable.getPageSize());

        List<AdminUserResponse> resultList = query.getResultList();
        long total = (long) countQuery.getSingleResult();

        return new PageImpl<>(resultList, pageable, total);
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


    //---- 메서드 분리 ----//
    //---- 메서드 분리 ----//
    // 페이징 관련 메서드 분리
    private Pageable getPageable(final Integer page, final String sort) {
        return PageRequest.of(page - 1, 10, Sort.by(sort != null ? sort : "id").descending());
    }

    // 목록 조회 필터링용 메서드 분리
    private String buildJpqlQuery(String type, boolean hasKeyword, String sortBy) {
        StringBuilder jpql = new StringBuilder("SELECT new com.tikitaka.triptroop.admin.dto.response.AdminUserResponse(u, p) ");
        jpql.append("FROM User u LEFT JOIN Profile p ON u.id = p.userId ");

        if (hasKeyword) {
            appendWhereClause(jpql, type);
        }

        jpql.append(" ORDER BY u.").append(sortBy);
        return jpql.toString();
    }

    private String buildCountJpqlQuery(String type, boolean hasKeyword) {
        StringBuilder countJpql = new StringBuilder("SELECT COUNT(u) ");
        countJpql.append("FROM User u LEFT JOIN Profile p ON u.id = p.userId ");

        if (hasKeyword) {
            appendWhereClause(countJpql, type);
        }

        return countJpql.toString();
    }

    private void appendWhereClause(StringBuilder jpql, String type) {
        if (type != null && !type.isEmpty()) {
            String columnName = getColumnName(type);
            jpql.append("WHERE ").append(columnName).append(" LIKE :keyword ");
        } else {
            List<String> columns = new ArrayList<>();
            columns.add("u.email LIKE :keyword");
            columns.add("p.nickname LIKE :keyword");
            columns.add("str(u.role) LIKE :keyword");
            columns.add("str(u.status) LIKE :keyword");
            jpql.append("WHERE ").append(String.join(" OR ", columns)).append(" ");
        }
    }

    private String getColumnName(String type) {
        switch (type) {
            case "email":
                return "u.email";
            case "nickname":
                return "p.nickname";
            case "role":
                return "str(u.role)";
            case "status":
                return "str(u.status)";
            default:
                throw new IllegalArgumentException("Invalid type: " + type);
        }
    }

    // 회원 등록 메서드 분리
    private void checkForDuplicates(AdminUserSaveRequest adminUserSaveRequest) {
        userService.checkEmailDuplicate(adminUserSaveRequest.getEmail());
        profileService.checkNicknameDuplicate(adminUserSaveRequest.getNickname());
    }
}



