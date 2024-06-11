package com.tikitaka.triptroop.user.domain.repository;

import com.tikitaka.triptroop.user.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Boolean existsByEmail(String email);

    Optional<User> findByEmail(String email);

    Optional<User> findByRefreshToken(String refreshToken);

    @Query("SELECT r.refreshToken FROM User r WHERE r.email = :email")
    String findRefreshTokenByEmail(@Param("email") String email);

    List<User> findByIdIn(List<Long> userIds);
}
