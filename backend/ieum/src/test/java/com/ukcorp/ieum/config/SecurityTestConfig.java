package com.ukcorp.ieum.config;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@TestConfiguration
public class SecurityTestConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                // token을 사용하는 방식이기 때문에 csrf를 disable
                .csrf(AbstractHttpConfigurer::disable)

                // Entry Point, Handler 추가
//                .addFilterBefore(corsFilter, UsernamePasswordAuthenticationFilter.class)
//                .exceptionHandling(exceptionHandling -> exceptionHandling
//                        .accessDeniedHandler(jwtAccessDeniedHandler)
//                        .authenticationEntryPoint(jwtAuthenticationEntryPoint)
//                )

                // HttpServletRequest에 대한 접근 제한 설정
                .authorizeHttpRequests(httpRequest -> httpRequest
                                // 인증없이 접근 허용
                                .requestMatchers("/api/member/join",
                                        "/api/member/login",
                                        "/api/member/check-id/*",
                                        "/api/member/check-email",
                                        "/api/member/check-phone",
                                        "/api/member/check-exist",
                                        "/api/member/auth",
                                        "/api/member/verify",
                                        "/api/member/password",
                                        "/api/devices/*",
                                        "/error").permitAll()
                                // 나머지 요청에 대해서는 인증 필요
//                        .anyRequest().permitAll()
                                .anyRequest().authenticated()
                )

                // 세션을 사용하지 않기 때문에 STATELESS로 설정
                .sessionManagement(sessionManagement ->
                        sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                );

                // JwtFilter 등록한 JwtSecurityConfig 적용
//                .with(new JwtSecurityConfig(tokenProvider), customizer -> {
//                });
        return http.build();
    }
}
