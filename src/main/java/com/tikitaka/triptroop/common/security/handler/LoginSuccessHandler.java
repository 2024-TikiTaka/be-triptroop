package com.tikitaka.triptroop.common.security.handler;


import com.tikitaka.triptroop.common.security.util.TokenUtils;
import com.tikitaka.triptroop.user.service.AuthService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import java.io.IOException;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
public class LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final AuthService authService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        Map<String, Object> userInfo = getUserInfo(authentication);

        String accessToken = TokenUtils.createAccessToken(userInfo);
        String refreshToken = TokenUtils.createRefreshToken();

        authService.updateRefreshToken((String) userInfo.get("email"), refreshToken);

        /* 응답 헤더에 발급 된 토큰을 담는다. */
        response.setHeader("Access-Token", accessToken);
        response.setHeader("Refresh-Token", refreshToken);
        response.setStatus(HttpServletResponse.SC_OK);
    }

    private Map<String, Object> getUserInfo(Authentication authentication) {

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String userRole = userDetails.getAuthorities()
                                     .stream().map(auth -> auth.getAuthority().toString())
                                     .collect(Collectors.joining());

        return Map.of(
                "email", userDetails.getUsername(),
                "role", userRole
        );
    }
}
