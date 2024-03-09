package com.ukcorp.ieum.config;

import com.ukcorp.ieum.jwt.JwtFilter;
import com.ukcorp.ieum.jwt.TokenProvider;
import lombok.AllArgsConstructor;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

// TokenProvider, JwtFilter를 SecurityConfig에 적용하기 위한 Class
@AllArgsConstructor
public class JwtSecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    // TokenProvider 주입
    private final TokenProvider tokenProvider;

    /**
     * JwtFilter를 통해 Security 로직에 Filter 등록
     *
     * @param http
     */
    @Override
    public void configure(HttpSecurity http) {
        http.addFilterBefore(
                new JwtFilter(tokenProvider),
                UsernamePasswordAuthenticationFilter.class
        );
    }
}