package com.tikitaka.triptroop.user.service;

import com.tikitaka.triptroop.common.exception.ConflictException;
import com.tikitaka.triptroop.common.exception.NotFoundException;
import com.tikitaka.triptroop.common.exception.type.ExceptionCode;
import com.tikitaka.triptroop.user.domain.entity.User;
import com.tikitaka.triptroop.user.domain.repository.UserRepository;
import com.tikitaka.triptroop.user.dto.request.SignUpRequest;
import com.tikitaka.triptroop.user.dto.response.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    /**
     * 이메일 가입 여부
     */
    @Transactional(readOnly = true)
    public void existsByEmail(String email) {
        if (userRepository.existsByEmail(email)) {
            throw new ConflictException(ExceptionCode.ALREADY_EXISTS_EMAIL);
        }
    }

    /**
     * 회원 번호로 조회
     */
    @Transactional(readOnly = true)
    public UserResponse findById(Long userId) {

        return UserResponse.from(
                userRepository.findById(userId)
                              .orElseThrow(() -> new NotFoundException(ExceptionCode.NOT_FOUND_USER))
        );
    }

    /**
     * 이메일로 조회
     */
    @Transactional(readOnly = true)
    public UserResponse findByEmail(String email) {
        return UserResponse.from(
                userRepository.findByEmail(email)
                              .orElseThrow(() -> new NotFoundException(ExceptionCode.NOT_FOUND_USER))
        );
    }

    /**
     * 회원 가입
     */
    @Transactional
    public void signup(final SignUpRequest userRequest) {

        existsByEmail(userRequest.getEmail());

        final User newUser = User.from(
                userRequest.getEmail(),
                encode(userRequest.getPassword()),
                userRequest.getName(),
                userRequest.getBirth(),
                userRequest.getGender()
        );

        userRepository.save(newUser);
    }


    private String encode(String password) {
        return passwordEncoder.encode(password);
    }
}
