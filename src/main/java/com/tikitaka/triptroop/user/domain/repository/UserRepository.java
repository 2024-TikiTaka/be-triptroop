package com.tikitaka.triptroop.user.domain.repository;

import com.tikitaka.triptroop.user.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Boolean existsByEmail(String email);

    Optional<User> findByEmail(String email);

    Optional<User> findByRefreshToken(String refreshToken);

    List<User> findByIdIn(List<Long> userIds);

}
