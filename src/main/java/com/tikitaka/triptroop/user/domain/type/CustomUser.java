package com.tikitaka.triptroop.user.domain.type;

import com.tikitaka.triptroop.user.domain.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;

import lombok.Getter;

@Getter
public class CustomUser implements OAuth2User, UserDetails {

    private Long userId;

    private String email;

    private String password;

    private Collection<? extends GrantedAuthority> authorities;

    private Map<String, Object> attributes;

    public CustomUser(Long userId, String email, String password, UserRole role) {
        this.userId = userId;
        this.email = email;
        this.password = password;
        this.authorities = Collections.singletonList(new SimpleGrantedAuthority(role.name()));
    }

    public CustomUser(User user, Map<String, Object> attributes) {
        this(user.getId(), user.getEmail(), user.getPassword(), user.getRole());
        this.attributes = attributes;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return attributes;
    }

    @Override
    public String getName() {
        return String.valueOf(userId);
    }
}