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
    private final UserInterestRepository userInterestRepository;
    private final InterestRepository interestRepository;

    @PersistenceContext
    private final EntityManager entityManager;


//    private Pageable getPageable(final Integer page, final String sort) {
//        return PageRequest.of(page - 1, 10, Sort.by(sort != null ? sort : "id").descending());
//    }
//
//    //    private Pageable getPageable(final Integer page) {
////        return getPageable(page, null);
////    }
//    private Pageable getPageable(final Integer page, final Integer pageSize) {
//        return PageRequest.of(page - 1, pageSize);
//    }

//    private Pageable getPageable(final Integer page, final String sort) {
//        return PageRequest.of(page - 1, 10, Sort.by(sort != null ? sort : "userId").descending());
//    }
//
//    private Pageable getPageable(final Integer page, final Integer pageSize) {
//        return PageRequest.of(page - 1, pageSize);
//    }

    /* 1. 관리자 회원 관리 - 회원 목록 조회 */
//    @Transactional(readOnly = true)
//    public Page<AdminUserResponse> findAdminUsers(final Integer page, final String type, final String keyword, final String sort) {
//        // sort가 null인 경우 기본값 설정
//        String sortBy = (sort == null || sort.isEmpty()) ? "userId" : sort;
//        System.out.println("Sortby : " + sortBy);
//        Pageable pageable = getPageable(page, sortBy);
//
////        Page<AdminUserResponse> adminUsers = null;
////        switch (type) {
////            case "All" : return adminUsers = adminUserRepository.findAdminUsersByKeyword(pageable,, keyword) ;
////            case "email" : return adminUsers = adminUserRepository.findAdminUsersByKeyword()
////            case "nickname" : return =
////            case "role" : return =
////            case "status" : return =
////        }
//
//        //Page<AdminUserResponse> adminUsers = adminUserRepository.findAdminUsersByKeyword(pageable, type, keyword, sort);
//
//
////        return adminUserRepository.findAdminUsersAll(pageable);
//        // return adminUsers;
////
////        Specification<User> spec = (root, query, cb) -> {
////            String columnName;
////            switch (type) {
////                case "email":
////                    columnName = "email";
////                    break;
////                case "nickname":
////                    columnName = "nickname";
////                    break;
////                default:
////                    throw new IllegalArgumentException("Invalid type: " + type);
////            }
////            return cb.like(root.get(columnName), "%" + keyword + "%");
////        };
////
////        return userRepository.findAll(spec, pageable).map(AdminUserResponse::new);
//
//        String columnName;
//        switch (type) {
//            case "email":
//                columnName = "u.email";
//                break;
//            case "nickname":
//                columnName = "p.nickname";
//                break;
//            default:
//                throw new IllegalArgumentException("Invalid type: " + type);
//        }
//        System.out.println("Column name: {} : " + columnName);
//
//        String jpql = "SELECT new com.tikitaka.triptroop.admin.dto.response.AdminUserResponse(u, p) " +
//                "FROM User u LEFT JOIN Profile p ON u.id = p.userId " +
//                "WHERE " + columnName + " LIKE :keyword";
//        jpql += " ORDER BY u." + sortBy;
//        System.out.println("JPQL Query: {}" + jpql);
//
//
//        Query query = entityManager.createQuery(jpql, AdminUserResponse.class);
//        query.setParameter("keyword", "%" + keyword + "%");
//
//        // Apply pagination
////        int totalRows = query.getResultList().size();
////        query.setFirstResult((int) pageable.getOffset());
////        query.setMaxResults(pageable.getPageSize());
////
////        List<AdminUserResponse> resultList = query.getResultList();
////        return new PageImpl<>(resultList, pageable, totalRows);
//// Apply pagination
//        query.setFirstResult((int) pageable.getOffset());
//        query.setMaxResults(pageable.getPageSize());
//
//        List<AdminUserResponse> resultList = query.getResultList();
//        System.out.println("Result list size: {}" + resultList.size());
//
//        // Total count query
//        String countJpql = "SELECT COUNT(u) " +
//                "FROM User u LEFT JOIN Profile p ON u.id = p.userId " +
//                "WHERE " + columnName + " LIKE :keyword";
//        Query countQuery = entityManager.createQuery(countJpql);
//        countQuery.setParameter("keyword", "%" + keyword + "%");
//        long total = (long) countQuery.getSingleResult();
//        System.out.println("TotalCount : " + total);
//
//        return new PageImpl<>(resultList, pageable, total);
//    }
    private Pageable getPageable(final Integer page, final String sort) {
        return PageRequest.of(page - 1, 10, Sort.by(sort != null ? sort : "id").descending());
    }

    private Pageable getPageable(final Integer page, final Integer pageSize) {
        return PageRequest.of(page - 1, pageSize);
    }

    @Transactional(readOnly = true)
    public Page<AdminUserResponse> findAdminUsers(final Integer page, final String type, final String keyword, final String sort) {
        // sort가 null인 경우 기본값 설정
        String sortBy = (sort == null || sort.isEmpty()) ? "id" : sort;
        System.out.println("Sortby: " + sortBy);

        Pageable pageable = getPageable(page, sortBy);

        String jpql = "SELECT new com.tikitaka.triptroop.admin.dto.response.AdminUserResponse(u, p) " +
                "FROM User u LEFT JOIN Profile p ON u.id = p.userId ";

        boolean hasKeyword = keyword != null && !keyword.isEmpty();
        boolean hasType = type != null && !type.isEmpty();

        if (hasType) {
            String columnName;
            switch (type) {
                case "email":
                    columnName = "u.email";
                    jpql += "WHERE " + columnName + " LIKE :keyword ";
                    break;
                case "nickname":
                    columnName = "p.nickname";
                    jpql += "WHERE " + columnName + " LIKE :keyword ";
                    break;
                case "role":
                    columnName = "u.role";
                    jpql += "WHERE str(" + columnName + ") LIKE :keyword ";
                    break;
                case "status":
                    columnName = "u.status";
                    jpql += "WHERE str(" + columnName + ") LIKE :keyword ";
                    break;
                default:
                    throw new IllegalArgumentException("Invalid type: " + type);
            }
        } else if (hasKeyword) {
            jpql += "WHERE u.email LIKE :keyword OR p.nickname LIKE :keyword OR str(u.role) LIKE :keyword OR str(u.status) LIKE :keyword ";
        }

        jpql += "ORDER BY u." + sortBy;
        System.out.println("JPQL Query: " + jpql);

        Query query = entityManager.createQuery(jpql, AdminUserResponse.class);
        if (hasKeyword) {
            query.setParameter("keyword", "%" + keyword + "%");
        }

        // Apply pagination
        query.setFirstResult((int) pageable.getOffset());
        query.setMaxResults(pageable.getPageSize());

        List<AdminUserResponse> resultList = query.getResultList();
        System.out.println("Result list size: " + resultList.size());

        // Total count query
        String countJpql = "SELECT COUNT(u) " +
                "FROM User u LEFT JOIN Profile p ON u.id = p.userId ";

        if (hasType) {
            String columnName;
            switch (type) {
                case "email":
                    columnName = "u.email";
                    countJpql += "WHERE " + columnName + " LIKE :keyword ";
                    break;
                case "nickname":
                    columnName = "p.nickname";
                    countJpql += "WHERE " + columnName + " LIKE :keyword ";
                    break;
                case "role":
                    columnName = "u.role";
                    countJpql += "WHERE str(" + columnName + ") LIKE :keyword ";
                    break;
                case "status":
                    columnName = "u.status";
                    countJpql += "WHERE str(" + columnName + ") LIKE :keyword ";
                    break;
                default:
                    throw new IllegalArgumentException("Invalid type: " + type);
            }
        } else if (hasKeyword) {
            countJpql += "WHERE u.email LIKE :keyword OR p.nickname LIKE :keyword OR str(u.role) LIKE :keyword OR str(u.status) LIKE :keyword ";
        }

        System.out.println("Count JPQL Query: " + countJpql);

        Query countQuery = entityManager.createQuery(countJpql);
        if (hasKeyword) {
            countQuery.setParameter("keyword", "%" + keyword + "%");
        }
        long total = (long) countQuery.getSingleResult();
        System.out.println("Total count: " + total);

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



