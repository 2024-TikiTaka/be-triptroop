package com.tikitaka.triptroop.user.domain.repository;

import com.tikitaka.triptroop.user.domain.entity.Profile;
import com.tikitaka.triptroop.user.dto.response.UserProfileResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProfileRepository extends JpaRepository<Profile, Long> {

    Boolean existsProfileByUserId(Long userId);

    Boolean existsByNickname(String nickname);

    Optional<Profile> findProfileByUserId(Long userId);

    /* 다솔 - 신고 목록 닉네임 조회 테스트 */
    Optional<Profile> findByNickname(String nickname);

    @Query("SELECT new com.tikitaka.triptroop.user.dto.response.UserProfileResponse(u, p)" +
            "FROM Profile p LEFT JOIN User u ON u.id = p.userId " +
            "WHERE u.status = 'ACTIVE' AND p.userId = :userId")
    Optional<UserProfileResponse> findUserProfileByUserId(@Param("userId") Long userId);

    @Query("SELECT new com.tikitaka.triptroop.user.dto.response.UserProfileResponse(u, p)" +
            "FROM Profile p LEFT JOIN User u ON u.id = p.userId " +
            "WHERE u.status = 'ACTIVE' AND p.nickname = :nickname")
    Optional<UserProfileResponse> findUserProfileByNickname(@Param("nickname") String nickname);

    @Query("SELECT new com.tikitaka.triptroop.user.dto.response.UserProfileResponse(u, p)" +
            "FROM Profile p LEFT JOIN User u ON u.id = p.userId " +
            "WHERE u.status = 'ACTIVE' AND u.id IN :userIds")
    List<UserProfileResponse> findUserProfileListByUserId(@Param("userIds") List<Long> userIds);
}
