package com.tikitaka.triptroop.common.config;

import com.tikitaka.triptroop.common.security.filter.CustomAuthenticationFilter;
import com.tikitaka.triptroop.common.security.filter.JwtAuthenticationFilter;
import com.tikitaka.triptroop.common.security.handler.JwtAccessDeniedHandler;
import com.tikitaka.triptroop.common.security.handler.JwtAuthenticationEntryPoint;
import com.tikitaka.triptroop.common.security.handler.LoginFailureHandler;
import com.tikitaka.triptroop.common.security.handler.LoginSuccessHandler;
import com.tikitaka.triptroop.user.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final AuthService authService;

    private final PasswordEncoder passwordEncoder;

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        return http.
                csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(sessionManage -> sessionManage.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .formLogin(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> {
                    auth.requestMatchers(HttpMethod.OPTIONS, "/**").permitAll();
                    /* TODO :: 추후 설정 */
                    auth.requestMatchers(HttpMethod.GET, "/images/**", "/api/v1/travels/**").permitAll();
                    auth.requestMatchers(HttpMethod.POST, "/api/v1/signup", "/api/v1/login").permitAll();
                    auth.requestMatchers("/api/v1/admin/**").hasRole("ADMIN");
                    auth.anyRequest().authenticated();
                    // auth.anyRequest().permitAll();
                })
                .addFilterBefore(customAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(jwtAuthenticationFilter(), BasicAuthenticationFilter.class)
                .exceptionHandling(exceptionHandling -> {
                    exceptionHandling.accessDeniedHandler(jwtAccessDeniedHandler());
                    exceptionHandling.authenticationEntryPoint(jwtAuthenticationEntryPoint());
                })
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .build();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        /* TODO :: 추후 설정 */
        // corsConfiguration.setAllowedOrigins(Arrays.asList("http://localhost:3000"));
        corsConfiguration.setAllowedMethods(Arrays.asList("GET", "PUT", "POST", "DELETE"));
        corsConfiguration.setAllowedHeaders(Arrays.asList(
                "Access-Control-Allow-Origin", "Access-Control-Allow-Headers",
                "Content-Type", "Authorization", "X-Requested-With", "Access-Token", "Refresh-Token"));
        corsConfiguration.setExposedHeaders(Arrays.asList("Access-Token", "Refresh-Token"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfiguration);
        return source;
    }

    @Bean
    AuthenticationManager authenticationManager() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder);
        provider.setUserDetailsService(authService);
        return new ProviderManager(provider);
    }

    @Bean
    LoginFailureHandler loginFailureHandler() {
        return new LoginFailureHandler();
    }

    @Bean
    LoginSuccessHandler loginSuccessHandler() {
        return new LoginSuccessHandler(authService);
    }

    @Bean
    CustomAuthenticationFilter customAuthenticationFilter() {

        CustomAuthenticationFilter customAuthenticationFilter = new CustomAuthenticationFilter();

        customAuthenticationFilter.setAuthenticationManager(authenticationManager());
        customAuthenticationFilter.setAuthenticationFailureHandler(loginFailureHandler());
        customAuthenticationFilter.setAuthenticationSuccessHandler(loginSuccessHandler());

        return customAuthenticationFilter;
    }

    @Bean
    JwtAuthenticationFilter jwtAuthenticationFilter() {
        return new JwtAuthenticationFilter(authService);
    }

    @Bean
    JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint() {
        return new JwtAuthenticationEntryPoint();
    }

    @Bean
    JwtAccessDeniedHandler jwtAccessDeniedHandler() {
        return new JwtAccessDeniedHandler();
    }
}
