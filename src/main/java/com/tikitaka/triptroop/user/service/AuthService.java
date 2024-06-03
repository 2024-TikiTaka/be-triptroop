package com.tikitaka.triptroop.user.service;


import com.tikitaka.triptroop.common.exception.NotFoundException;
import com.tikitaka.triptroop.common.exception.type.ExceptionCode;
import com.tikitaka.triptroop.common.security.dto.LoginDto;
import com.tikitaka.triptroop.common.security.dto.TokenDto;
import com.tikitaka.triptroop.common.security.util.TokenUtils;
import com.tikitaka.triptroop.user.domain.entity.User;
import com.tikitaka.triptroop.user.domain.repository.UserRepository;
import com.tikitaka.triptroop.user.domain.type.CustomUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Map;

import static com.tikitaka.triptroop.common.exception.type.ExceptionCode.NOT_FOUND_REFRESH_TOKEN;

@Service
@RequiredArgsConstructor
public class AuthService implements UserDetailsService {

    private final UserRepository userRepository;

    /**
     * 회원 조회
     */
    @Override
    public CustomUser loadUserByUsername(String email) throws UsernameNotFoundException {

        final User user = userRepository.findByEmail(email)
                                        .orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을 수 없습니다."));

        return new CustomUser(user.getId(), user.getEmail(), user.getPassword(), user.getRole());
    }

    /**
     * RefreshToken 으로 조회
     */
    public LoginDto findByRefreshToken(String refreshToken) {
        return LoginDto.from(
                userRepository.findByRefreshToken(refreshToken)
                              .orElseThrow(() -> new NotFoundException(NOT_FOUND_REFRESH_TOKEN))
        );
    }

    /**
     * RefreshToken 검증 및 재발급
     */
    public TokenDto checkRefreshTokenAndReIssueToken(String refreshToken) {

        LoginDto loginDto = findByRefreshToken(refreshToken);
        String reIssuedRefreshToken = TokenUtils.createRefreshToken();
        String reIssuedAccessToken = TokenUtils.createAccessToken(getUserInfo(loginDto));

        updateRefreshToken(loginDto.getEmail(), reIssuedRefreshToken);

        return TokenDto.of(reIssuedAccessToken, reIssuedRefreshToken);
    }

    /**
     * RefreshToken 수정
     */
    public void updateRefreshToken(String email, String refreshToken) {

        final User user = userRepository.findByEmail(email)
                                        .orElseThrow(() -> new NotFoundException(ExceptionCode.NOT_FOUND_USER));

        user.updateRefreshToken(refreshToken);
    }

    /**
     * Authentication 저장
     */
    public void saveAuthentication(String email) {

        CustomUser user = loadUserByUsername(email);

        Authentication authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());

        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    private Map<String, Object> getUserInfo(LoginDto loginDto) {
        return Map.of(
                "email", loginDto.getEmail(),
                "role", loginDto.getRole()
        );
    }
}
