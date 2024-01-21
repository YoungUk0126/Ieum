package com.ukcorp.ieum.jwt;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

@NoArgsConstructor
@Slf4j
public class JwtUtil {

    public static Optional<String> getUserId() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null) {
            log.debug("Security Context에 인증 정보가 없습니다.");
            return Optional.empty();
        }

        String userId = null;
        if (authentication.getPrincipal() instanceof CustomUserDetails) {
            CustomUserDetails loginUser = (CustomUserDetails) authentication.getPrincipal();
            userId = loginUser.getUserId();
        } else if (authentication.getPrincipal() instanceof String) {
            userId = (String) authentication.getPrincipal();
        }

        return Optional.ofNullable(userId);
    }
}
