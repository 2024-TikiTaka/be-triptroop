package com.tikitaka.triptroop.user.domain.repository;

import com.tikitaka.triptroop.user.domain.entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProfileRepository extends JpaRepository<Profile, Long> {

    Optional<Profile> findByUserId(Long userId);

    /* 다솔 - 신고 목록 닉네임 조회 테스트 */
    Profile findByNickname(String nickname);

    List<Profile> findByUserIdIn(List<Long> userIds);
}
