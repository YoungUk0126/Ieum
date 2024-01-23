package com.ukcorp.ieum.jwt;

import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

// 권한이 없는 토큰에 대해 403 반환할 Handler
@Component
public class JwtAccessDeniedHandler implements AccessDeniedHandler {

    /**
     * 권한이 없는 토큰으로 접근 시 처리할 Method
     *
     * @param request               that resulted in an <code>AccessDeniedException</code>
     * @param response              so that the user agent can be advised of the failure
     * @param accessDeniedException that caused the invocation
     * @throws IOException
     */
    @Override
    public void handle(HttpServletRequest request,
                       HttpServletResponse response,
                       AccessDeniedException accessDeniedException) throws IOException {
        if (accessDeniedException.getCause() instanceof ExpiredJwtException) {
            // 만료된 토큰에 대한 응답 처리
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "Token expired");
        } else {
            //필요한 권한이 없이 접근하려 할때 403
            response.sendError(HttpServletResponse.SC_FORBIDDEN);
        }
    }
}