package com.tikitaka.triptroop.user.service;

import com.tikitaka.triptroop.common.exception.ConflictException;
import com.tikitaka.triptroop.common.exception.type.ExceptionCode;
import com.tikitaka.triptroop.user.domain.entity.User;
import com.tikitaka.triptroop.user.domain.repository.UserRepository;
import com.tikitaka.triptroop.user.dto.request.SignUpRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

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
        log.info("✅signUp ::: newUser : {} ", newUser);
    }

    @Transactional(readOnly = true)
    public void existsByEmail(String email) {
        log.info("={}", userRepository.existsByEmail(email));
        if (userRepository.existsByEmail(email)) {
            throw new ConflictException(ExceptionCode.ALREADY_EXISTS_EMAIL);
        }
    }
}
