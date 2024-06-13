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

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    /**
     * 회원 존재 여부
     *
     * @param email 이메일
     */
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    /**
     * 이메일 가입 여부
     *
     * @param email
     */
    public void checkEmailDuplicate(String email) {

        if (existsByEmail(email)) {
            throw new ConflictException(ExceptionCode.ALREADY_EXISTS_EMAIL);
        }
    }

    /**
     * 회원 조회
     *
     * @param userId 회원번호
     * @return UserResponse
     */
    public UserResponse findById(Long userId) {

        return UserResponse.from(
                userRepository.findById(userId)
                              .orElseThrow(() -> new NotFoundException(ExceptionCode.NOT_FOUND_USER))
        );
    }

    /**
     * 회원 조회
     *
     * @param email 이메일
     * @return UserResponse
     */
    public UserResponse findByEmail(String email) {

        return UserResponse.from(
                userRepository.findByEmail(email)
                              .orElseThrow(() -> new NotFoundException(ExceptionCode.NOT_FOUND_USER))
        );
    }

    /**
     * 회원 가입
     *
     * @param userRequest
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
                LocalDate.parse(userRequest.getBirth(), DateTimeFormatter.ofPattern("yyyyMMdd")),
                userRequest.getGender()
        );

        userRepository.save(newUser);
    }

    /**
     * 비밀번호 변경
     *
     * @param email
     * @param password
     */

    public void resetPassword(String email, String password) {

        final User user = userRepository.findByEmail(email)
                                        .orElseThrow(() -> new NotFoundException(ExceptionCode.NOT_FOUND_USER));

        user.updatePassword(encode(password));
    }

    /**
     * 비밀번호 변경
     *
     * @param userId
     * @param passwordRequest
     */
    @Transactional
    public void changePassword(Long userId, PasswordRequest passwordRequest) {

        final User user = validatePassword(userId, passwordRequest.getCurrentPassword());
        user.updatePassword(encode(passwordRequest.getNewPassword()));
    }

    /**
     * 회원 수정
     *
     * @param userId
     * @param userRequest
     * @return UserResponse
     */
    @Transactional
    public UserResponse updateUser(Long userId, UserSaveRequest userRequest) {

        final User user = userRepository.findById(userId)
                                        .orElseThrow(() -> new NotFoundException(ExceptionCode.NOT_FOUND_USER));
        user.updateUser(userRequest.getPhone());

        return UserResponse.from(user);
    }

    /**
     * 회원 탈퇴
     *
     * @param userId
     * @param currentPassword
     */
    @Transactional
    public void withdrawal(Long userId, String currentPassword) {

        final User user = validatePassword(userId, currentPassword);

        if (user.isWithdrawnUser()) {
            throw new BadRequestException(ExceptionCode.ALREADY_WITHDRAWN_USER);
        }

        userRepository.delete(user);
    }

    private User validatePassword(Long userId, String password) {

        final User user = userRepository.findById(userId)
                                        .orElseThrow(() -> new NotFoundException(ExceptionCode.NOT_FOUND_USER));

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new BadRequestException(ExceptionCode.INVALID_PASSWORD);
        }

        return user;
    }

    private String encode(String password) {

        return passwordEncoder.encode(password);
    }


}
