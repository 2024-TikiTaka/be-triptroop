package com.tikitaka.triptroop.common.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tikitaka.triptroop.common.dto.response.ApiResponse;
import com.tikitaka.triptroop.common.exception.dto.response.ErrorResponse;
import com.tikitaka.triptroop.common.exception.type.ExceptionCode;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
public class OAuth2LoginFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {

        log.info("✅OAuth2LoginFailureHandler.onAuthenticationFailure");

        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json;charset=UTF-8");

        ExceptionCode exceptionCode;

        if (exception instanceof OAuth2AuthenticationException) {
            String errorCode = ((OAuth2AuthenticationException) exception).getError().getErrorCode();
            exceptionCode = getOAuth2ExceptionCode(errorCode);
        } else {
            exceptionCode = ExceptionCode.INVALID_CREDENTIALS;
        }

        response.getWriter().write(objectMapper.writeValueAsString(
                ApiResponse.fail(new ErrorResponse(exceptionCode))
        ));
    }

    private ExceptionCode getOAuth2ExceptionCode(String errorCode) {
        switch (errorCode) {
            case "invalid_grant":
                return ExceptionCode.OAUTH2_LOGIN_FAILED;
            case "unsupported_grant_type":
                return ExceptionCode.OAUTH2_PROVIDER_NOT_SUPPORTED;
            default:
                return ExceptionCode.OAUTH2_USER_INFO_FETCH_FAILED;
        }
    }
}
