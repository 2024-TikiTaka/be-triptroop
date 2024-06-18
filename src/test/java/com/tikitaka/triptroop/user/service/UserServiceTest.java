package com.tikitaka.triptroop.user.service;

import com.tikitaka.triptroop.user.domain.entity.User;
import com.tikitaka.triptroop.user.domain.repository.UserRepository;
import com.tikitaka.triptroop.user.dto.request.SignUpRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
class UserServiceTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @DisplayName("회원가입_테스트_성공")
    @Test
    @Transactional
    void 회원가입_테스트_성공() {
        SignUpRequest newUser = new SignUpRequest(
                "hello@email.com",
                "hello!234VV",
                "김아현",
                "19960504",
                "F"
        );
        userService.signup(newUser);

        User user = userRepository.findByEmail(newUser.getEmail()).orElseThrow();
        System.out.println("============" + user);
        Assertions.assertEquals(user.getEmail(), newUser.getEmail());
    }

    // @DisplayName("회원가입_테스트_실패")
    // @Test
    // @Transactional
    // void 회원가입_테스트_실패() {
    //     SignUpRequest newUser = new SignUpRequest(
    //             "hello@email.com",
    //             "hello!234VV",
    //             "김아현",
    //             LocalDate.parse("1996-05-04"),
    //             "F"
    //     );
    //     SignUpRequest newUser2 = new SignUpRequest(
    //             "hello@email.com",
    //             "hello!234VV",
    //             "김아현",
    //             LocalDate.parse("1996-05-04"),
    //             "F"
    //     );
    //     userService.signup(newUser);
    //     // userService.signup(newUser);
    // }
}