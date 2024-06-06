package com.tikitaka.triptroop.admin.domain;

import com.tikitaka.triptroop.admin.dto.response.AdminUserResponse;
import com.tikitaka.triptroop.user.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AdminUserRepository extends JpaRepository<User, Long> {


    @Query("SELECT new com.tikitaka.triptroop.admin.dto.response.AdminUserResponse(u, p)" +
            "FROM User u LEFT JOIN Profile p ON u.id = p.userId ")
    List<AdminUserResponse> findAdminUsersAll();

}
