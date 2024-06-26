package com.tikitaka.triptroop.admin.domain.repository;

import com.tikitaka.triptroop.admin.dto.response.AdminUserDetailResponse;
import com.tikitaka.triptroop.user.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface AdminUserRepository extends JpaRepository<User, Long> {

    /* 1. 관리자 회원 목록 조회 */
//    @Query("SELECT new com.tikitaka.triptroop.admin.dto.response.AdminUserResponse(u, p)" +
//            "FROM User u LEFT JOIN Profile p ON u.id = p.userId ")
//    Page<AdminUserResponse> findAdminUsersAll(Pageable pageable);
//    @Query("SELECT new com.tikitaka.triptroop.admin.dto.response.AdminUserResponse(u, p)" +
//            "FROM User u LEFT JOIN Profile p ON u.id = p.userId " +
//            "WHERE")
//    Page<AdminUserResponse> findAdminUsersByKeyword(Pageable pageable, String type, String keyword, String sort);

    /* 2. 관리자 회원 상세 조회 */
    @Query("SELECT new com.tikitaka.triptroop.admin.dto.response.AdminUserDetailResponse(" +
            "u.id, u.email, p.nickname, u.role, u.createdAt, u.name, u.gender, u.status, u.birth, u.phone, " +
            "(SELECT COUNT(r) FROM Report r WHERE r.reporterId = u.id), " +
            "su.provider, u.password, u.godo, " +
            "(SELECT AVG(ur.reviewPoint) FROM UserReview ur WHERE ur.reviewedUserId = u.id), " +
            "p.introduction, " +
            "CAST(GROUP_CONCAT(i.name) AS string), " +
            "(SELECT COUNT(i) FROM Inquiry i WHERE i.userId = u.id), " +
            "p.mbti, " +
            "(SELECT COUNT(s) FROM Schedule s WHERE s.userId = u.id) + " +
            "(SELECT COUNT(c) FROM Companion c WHERE c.userId = u.id) + " +
            "(SELECT COUNT(t) FROM Travel t WHERE t.userId = u.id), " +
            "(SELECT COUNT(f) FROM Friend f WHERE (f.requesterId = u.id OR f.accepterId = u.id) AND f.status = 'ACCEPTED' ) ) " +
            "FROM User u " +
            "LEFT JOIN Profile p ON u.id = p.userId " +
            "LEFT JOIN SocialUser su ON u.id = su.userId " +
            "LEFT JOIN UserInterest ui ON u.id = ui.userId " +
            "LEFT JOIN Interest i ON ui.interestId = i.id " +
            "WHERE u.id = :userId " +
            "GROUP BY u.id, p.nickname, su.provider, p.introduction, p.mbti")
    Optional<AdminUserDetailResponse> findAdminUserDetailByUserId(@Param("userId") Long userId);

}
