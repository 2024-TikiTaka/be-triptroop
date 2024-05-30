package com.tikitaka.triptroop.user.service;


import com.tikitaka.triptroop.common.security.dto.LoginDto;
import com.tikitaka.triptroop.common.security.dto.TokenDto;
import com.tikitaka.triptroop.common.security.util.TokenUtils;
import com.tikitaka.triptroop.user.domain.type.CustomUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthService implements UserDetailsService {

    private final UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        LoginDto loginDto = userService.findByEmail(username);

        return org.springframework.security.core.userdetails.User.builder()
                                                                 .username(loginDto.getEmail())
                                                                 .password(loginDto.getPassword())
                                                                 .roles(loginDto.getRole().name())
                                                                 .build();
    }

    /**
     * RefreshToken 수정
     */
    public void updateRefreshToken(String email, String refreshToken) {
        userService.updateRefreshToken(email, refreshToken);
    }

    /**
     * RefreshToken 검증 및 재발급
     */
    public TokenDto checkRefreshTokenAndReIssueToken(String refreshToken) {
        LoginDto loginDto = userService.findByRefreshToken(refreshToken);
        String reIssuedRefreshToken = TokenUtils.createRefreshToken();
        String reIssuedAccessToken = TokenUtils.createAccessToken(getUserInfo(loginDto));
        userService.updateRefreshToken(loginDto.getEmail(), reIssuedRefreshToken);
        return TokenDto.of(reIssuedAccessToken, reIssuedRefreshToken);
    }

    /**
     * Authentication 저장
     */
    public void saveAuthentication(String email) {

        LoginDto loginDto = userService.findByEmail(email);

        UserDetails user
                = org.springframework.security.core.userdetails.User.builder()
                                                                    .username(loginDto.getEmail())
                                                                    .password(loginDto.getPassword())
                                                                    .roles(loginDto.getRole().name())
                                                                    .build();

        CustomUser customUser = new CustomUser(loginDto.getUserId(), user);

        Authentication authentication
                = new UsernamePasswordAuthenticationToken(customUser, null, customUser.getAuthorities());

        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    private Map<String, Object> getUserInfo(LoginDto loginDto) {
        return Map.of(
                "username", loginDto.getEmail(),
                "role", loginDto.getRole()
        );
    }
}
