package com.tikitaka.triptroop.user.domain.repository;

import com.tikitaka.triptroop.user.domain.entity.Profile;
import com.tikitaka.triptroop.user.dto.response.CurrentUserResponse;
import com.tikitaka.triptroop.user.dto.response.UserProfileResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ProfileRepository extends JpaRepository<Profile, Long> {

    Boolean existsProfileByUserId(Long userId);

    Boolean existsByNickname(String nickname);

    Optional<Profile> findByUserId(Long userId);

    Optional<Profile> findByNickname(String nickname);

    @Query("SELECT new com.tikitaka.triptroop.user.dto.response.CurrentUserResponse(u, p)" +
            "FROM Profile p LEFT JOIN User u ON u.id = p.userId " +
            "WHERE u.status = 'ACTIVE' AND p.userId IN :userId")
    Optional<CurrentUserResponse> findCurrentUserProfile(@Param("userId") Long userId);

    @Query("SELECT new com.tikitaka.triptroop.user.dto.response.UserProfileResponse(u, p)" +
            "FROM Profile p LEFT JOIN User u ON u.id = p.userId " +
            "WHERE u.status = 'ACTIVE' AND p.id = :profileId")
    Optional<UserProfileResponse> findUserProfileById(@Param("profileId") Long profileId);

    @Query("SELECT new com.tikitaka.triptroop.user.dto.response.UserProfileResponse(u, p)" +
            "FROM Profile p LEFT JOIN User u ON u.id = p.userId " +
            "WHERE u.status = 'ACTIVE' AND p.userId = :userId")
    Optional<UserProfileResponse> findUserProfileByUserId(@Param("userId") Long userId);

    @Query("SELECT new com.tikitaka.triptroop.user.dto.response.UserProfileResponse(u, p)" +
            "FROM Profile p LEFT JOIN User u ON u.id = p.userId " +
            "WHERE u.status = 'ACTIVE' AND p.nickname = :nickname")
    Optional<UserProfileResponse> findUserProfileByNickname(@Param("nickname") String nickname);

}
