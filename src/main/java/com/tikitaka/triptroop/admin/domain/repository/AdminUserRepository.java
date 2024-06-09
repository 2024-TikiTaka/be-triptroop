package com.tikitaka.triptroop.admin.domain.repository;

import com.tikitaka.triptroop.admin.dto.response.AdminUserDetailResponse;
import com.tikitaka.triptroop.admin.dto.response.AdminUserResponse;
import com.tikitaka.triptroop.user.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface AdminUserRepository extends JpaRepository<User, Long> {

    /* 1. 관리자 회원 목록 조회 */
    @Query("SELECT new com.tikitaka.triptroop.admin.dto.response.AdminUserResponse(u, p)" +
            "FROM User u LEFT JOIN Profile p ON u.id = p.userId ")
    List<AdminUserResponse> findAdminUsersAll();

    /* 2. 관리자 회원 상세 조회 */
    /* 2. 관리자 회원 상세 조회 */
    @Query("SELECT new com.tikitaka.triptroop.admin.dto.response.AdminUserDetailResponse(" +
            "u.id, u.email, p.nickname, u.role, u.createdAt, u.name, u.gender, u.status, u.birth, u.phone, " +
            "(SELECT COUNT(r) FROM Report r WHERE r.reporterId = u.id), " +
            "su.provider, u.password, u.godo, 0, p.introduction, " +
            "GROUP_CONCAT(i.name), " +
            "(SELECT COUNT(i) FROM Inquirie i WHERE i.userId = u.id), " +
            "p.mbti, " +
            "(SELECT COUNT(s) FROM Schedule s WHERE s.userId = u.id) + " +
            "(SELECT COUNT(c) FROM Companion c WHERE c.userId = u.id) + " +
            "(SELECT COUNT(t) FROM Travel t WHERE t.userId = u.id), " +
            "(SELECT COUNT(f) FROM Friend f WHERE f.requesterId = u.id OR f.accepterId = u.id)) " +
            "FROM User u " +
            "LEFT JOIN Profile p ON u.id = p.userId " +
            "LEFT JOIN SocialUser su ON u.id = su.userId " +
            "LEFT JOIN UserInterest ui ON u.id = ui.userId " +
            "LEFT JOIN Interest i ON ui.interestId = i.id " +
            "WHERE u.id = :userId " +
            "GROUP BY u.id, p.nickname, su.provider, p.introduction, p.mbti")
    Optional<AdminUserDetailResponse> findAdminUserDetailByUserId(@Param("userId") Long userId);


}
