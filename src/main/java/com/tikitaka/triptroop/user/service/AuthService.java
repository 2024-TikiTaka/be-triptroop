package com.tikitaka.triptroop.user.service;


import com.tikitaka.triptroop.common.security.dto.LoginDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        LoginDto loginDto = new LoginDto();

        return User.builder()
                   .username(loginDto.getEmail())
                   .password(loginDto.getPassword())
                   .roles(loginDto.getRole().name())
                   .build();
    }
}
