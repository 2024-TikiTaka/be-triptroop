package com.tikitaka.triptroop.common.config;

import com.tikitaka.triptroop.common.security.filter.CustomAuthenticationFilter;
import com.tikitaka.triptroop.common.security.filter.JwtAuthenticationFilter;
import com.tikitaka.triptroop.common.security.handler.*;
import com.tikitaka.triptroop.user.service.AuthService;
import com.tikitaka.triptroop.user.service.OAuth2Service;
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

    private final OAuth2Service oAuth2Service;

    private final PasswordEncoder passwordEncoder;

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        return http.
                csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(sessionManage -> sessionManage.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .formLogin(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> {
                    /* ALL */
                    auth.requestMatchers(HttpMethod.OPTIONS, "/**").permitAll();
                    auth.requestMatchers(HttpMethod.GET,
                                         "/images/**",
                                         "/api/v1/check/**", "/api/v1/find/**", "/api/v1/admin/**",
                                         "/api/v1/travels/**", "/api/v1/schedules/**", "/api/v1/friend/**").permitAll();
                    auth.requestMatchers(HttpMethod.POST,
                                         "/api/v1/login/**", "/api/v1/signup/**",
                                         "/api/v1/email/**", "/api/v1/find/**",
                                         "/api/v1/users/**", "/api/v1/friend/**",
                                         "/api/v1/token/issue", "/api/v1/password/reset").permitAll();
                    auth.requestMatchers("/ws/**", "/api/v1/chat/**").hasAnyAuthority("ADMIN", "USER");
                    /* ADMIN */
                    auth.requestMatchers("/api/v1/admin/**").hasAuthority("ADMIN");
                    auth.anyRequest().authenticated();
                })
                // .oauth2Login(oauth2Login -> oauth2Login.userInfoEndpoint())
                // .oauth2Login(oauth2Login ->
                //                      oauth2Login
                //                              .authorizationEndpoint(auth -> auth.baseUri("/api/v1/login/oauth2/**"))
                //                              .userInfoEndpoint(userInfo -> userInfo.userService(oAuth2Service))
                //                              // .redirectionEndpoint(redirect -> redirect.baseUri("/oauth2/authorization/**"))
                //                              .successHandler(oAuth2LoginSuccessHandler())
                //                              .failureHandler(oAuth2LoginFailureHandler())
                // )
                // .addFilterBefore(customAuthenticationFilter(), OAuth2LoginAuthenticationFilter.class)
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
        corsConfiguration.setAllowedOrigins(Arrays.asList(
                "http://localhost:3000/",
                "https://fe-triptroop.vercel.app/"
        ));
        corsConfiguration.setAllowedMethods(Arrays.asList("GET", "PUT", "POST", "DELETE"));
        corsConfiguration.setAllowedHeaders(Arrays.asList("Access-Control-Allow-Origin",
                                                          "Access-Control-Allow-Headers",
                                                          "Content-Type",
                                                          "Authorization",
                                                          "X-Requested-With",
                                                          "Access-Token",
                                                          "Refresh-Token"));
        corsConfiguration.setExposedHeaders(Arrays.asList("Access-Token", "Refresh-Token"));
        corsConfiguration.setAllowCredentials(true);

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
    LoginSuccessHandler loginSuccessHandler() {
        return new LoginSuccessHandler(authService);
    }

    @Bean
    LoginFailureHandler loginFailureHandler() {
        return new LoginFailureHandler();
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
    OAuth2LoginFailureHandler oAuth2LoginFailureHandler() {
        return new OAuth2LoginFailureHandler();
    }

    @Bean
    OAuth2LoginSuccessHandler oAuth2LoginSuccessHandler() {
        return new OAuth2LoginSuccessHandler(oAuth2Service);
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
