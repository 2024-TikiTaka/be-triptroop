package com.tikitaka.triptroop.user.service;

import com.tikitaka.triptroop.common.exception.ConflictException;
import com.tikitaka.triptroop.common.exception.NotFoundException;
import com.tikitaka.triptroop.common.exception.type.ExceptionCode;
import com.tikitaka.triptroop.common.security.dto.LoginDto;
import com.tikitaka.triptroop.user.domain.entity.User;
import com.tikitaka.triptroop.user.domain.repository.UserRepository;
import com.tikitaka.triptroop.user.dto.request.SignUpRequest;
import com.tikitaka.triptroop.user.dto.request.UserRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.tikitaka.triptroop.common.exception.type.ExceptionCode.NOT_FOUND_REFRESH_TOKEN;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Transactional(readOnly = true)
    public void existsByEmail(String email) {
        if (userRepository.existsByEmail(email)) {
            throw new ConflictException(ExceptionCode.ALREADY_EXISTS_EMAIL);
        }
    }

    @Transactional(readOnly = true)
    public UserRequest findById(Long userId) {
        final User foundUser = userRepository.findById(userId)
                                             .orElseThrow(() -> new NotFoundException(ExceptionCode.NOT_FOUND_USER));
        return UserRequest.from(foundUser);
    }

    @Transactional(readOnly = true)
    public LoginDto findByEmail(String email) {
        final User foundUser = userRepository.findByEmail(email)
                                             .orElseThrow(() -> new UsernameNotFoundException("해당 아이디가 존재하지 않습니다."));
        return LoginDto.from(foundUser);
    }

    public LoginDto findByRefreshToken(String refreshToken) {
        final User foundUser = userRepository.findByRefreshToken(refreshToken)
                                             .orElseThrow(() -> new NotFoundException(NOT_FOUND_REFRESH_TOKEN));
        return LoginDto.from(foundUser);
    }

    @Transactional
    public void signup(final SignUpRequest userRequest) {

        existsByEmail(userRequest.getEmail());

        final User newUser = User.of(
                userRequest.getEmail(),
                passwordEncoder.encode(userRequest.getPassword()),
                userRequest.getName(),
                userRequest.getBirth(),
                userRequest.getGender()
        );

        userRepository.save(newUser);
    }

    public void updateRefreshToken(String email, String refreshToken) {
        final User user = userRepository.findByEmail(email)
                                        .orElseThrow(() -> new UsernameNotFoundException("해당 아이디가 존재하지 않습니다."));
        user.updateRefreshToken(refreshToken);
    }
}
