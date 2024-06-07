package com.tikitaka.triptroop.admin.domain;

import com.tikitaka.triptroop.admin.dto.response.AdminUserDetailResponse;
import com.tikitaka.triptroop.admin.dto.response.AdminUserResponse;
import com.tikitaka.triptroop.user.domain.entity.User;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AdminUserRepository extends JpaRepository<User, Long> {


    @Query("SELECT new com.tikitaka.triptroop.admin.dto.response.AdminUserResponse(u, p)" +
            "FROM User u LEFT JOIN Profile p ON u.id = p.userId ")
    List<AdminUserResponse> findAdminUsersAll();


    @Query("SELECT new com.tikitaka.triptroop.admin.dto.response.AdminUserDetailResponse(u, su, ui, i, ur, p) " +
            "FROM User u " +
            "LEFT JOIN Profile p ON u.id = p.userId " +
            "LEFT JOIN SocialUser su ON u.id = su.userId " +
            "LEFT JOIN UserReview ur ON u.id = ur.reviewedUserId " +
            "LEFT JOIN UserInterest ui ON u.id = ui.userId " +
            "LEFT JOIN Interest i ON ui.interestId = i.id " +
            "WHERE u.id = :userId"

    )
    Optional<AdminUserDetailResponse> findAdminUserDetailByUserId(@Param("userId") Long userId);

}
