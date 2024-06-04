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

    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    /**
     * 회원 번호로 조회
     */
    @Transactional(readOnly = true)
    public UserResponse findById(Long userId) {
        return UserResponse.from(findUserById(userId));
    }

    /**
     * 이메일로 조회
     */
    @Transactional(readOnly = true)
    public UserResponse findByEmail(String email) {
        return UserResponse.from(findUserByEmail(email));
    }

    /**
     * 이메일 가입 여부
     */
    public void checkEmailDuplicate(String email) {
        if (existsByEmail(email)) {
            throw new ConflictException(ExceptionCode.ALREADY_EXISTS_EMAIL);
        }
    }

    /**
     * 회원 가입
     */
    @Transactional
    public void signup(final SignUpRequest userRequest) {

        if (existsByEmail(userRequest.getEmail())) {
            throw new ConflictException(ExceptionCode.ALREADY_EXISTS_EMAIL);
        }

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
    @Transactional
    public UserResponse updateUser(Long userId, UserSaveRequest userRequest) {

        final User user = findUserById(userId);
        user.updateUser(userRequest.getPhone());

        return UserResponse.from(user);
    }

    /**
     * 회원 탈퇴
     */
    @Transactional
    public void withdrawal(Long userId, String currentPassword) {

        final User user = validatePassword(userId, currentPassword);

        if (user.isWithdrawnUser()) {
            throw new BadRequestException(ExceptionCode.ALREADY_WITHDRAWN_USER);
        }

        userRepository.delete(user);
    }

    private User findUserByEmail(String email) {

        return userRepository.findByEmail(email)
                             .orElseThrow(() -> new NotFoundException(ExceptionCode.NOT_FOUND_USER));
    }

    private User findUserById(Long userId) {

        return userRepository.findById(userId)
                             .orElseThrow(() -> new NotFoundException(ExceptionCode.NOT_FOUND_USER));
    }

    private User validatePassword(Long userId, String password) {

        final User user = findUserById(userId);

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new BadRequestException(ExceptionCode.INVALID_PASSWORD);
        }

        return user;
    }

    private String encode(String password) {

        return passwordEncoder.encode(password);
    }
}
