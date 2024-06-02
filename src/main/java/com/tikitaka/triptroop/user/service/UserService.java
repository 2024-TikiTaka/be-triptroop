package com.tikitaka.triptroop.user.service;

import com.tikitaka.triptroop.common.exception.BadRequestException;
import com.tikitaka.triptroop.common.exception.ConflictException;
import com.tikitaka.triptroop.common.exception.NotFoundException;
import com.tikitaka.triptroop.common.exception.type.ExceptionCode;
import com.tikitaka.triptroop.user.domain.entity.User;
import com.tikitaka.triptroop.user.domain.repository.UserRepository;
import com.tikitaka.triptroop.user.dto.request.PasswordRequest;
import com.tikitaka.triptroop.user.dto.request.SignUpRequest;
import com.tikitaka.triptroop.user.dto.request.UserSaveRequest;
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
        return UserResponse.from(findUser(userId));
    }

    /**
     * 이메일로 조회
     */
    @Transactional(readOnly = true)
    public UserResponse findByEmail(String email) {
        return UserResponse.from(findUser(email));
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

    /**
     * 비밀번호 변경
     */
    public void changePassword(Long userId, PasswordRequest passwordRequest) {

        final User user = validatePassword(userId, passwordRequest.getCurrentPassword());
        user.updatePassword(encode(passwordRequest.getNewPassword()));
    }

    /**
     * 전화번호 변경
     */
    public UserResponse updateUser(Long userId, UserSaveRequest userRequest) {

        final User user = findUser(userId);
        user.updateUser(userRequest.getPhone());

        return UserResponse.from(user);
    }

    /**
     * 회원 탈퇴
     */
    public void withdrawal(Long userId, String currentPassword) {

        final User user = validatePassword(userId, currentPassword);
        
        if (user.isWithdrawnUser()) {
            throw new BadRequestException(ExceptionCode.ALREADY_WITHDRAWN_USER);
        }

        userRepository.delete(user);
    }

    private User findUser(String email) {

        return userRepository.findByEmail(email)
                             .orElseThrow(() -> new NotFoundException(ExceptionCode.NOT_FOUND_USER));
    }

    private User findUser(Long userId) {

        return userRepository.findById(userId)
                             .orElseThrow(() -> new NotFoundException(ExceptionCode.NOT_FOUND_USER));
    }

    private User validatePassword(Long userId, String password) {

        final User user = findUser(userId);

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new BadRequestException(ExceptionCode.INVALID_PASSWORD);
        }

        return user;
    }

    private String encode(String password) {

        return passwordEncoder.encode(password);
    }
}
