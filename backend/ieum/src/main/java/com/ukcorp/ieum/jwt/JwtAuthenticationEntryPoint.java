package com.ukcorp.ieum.jwt;

import com.ukcorp.ieum.exception.ExpiredTokenException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;

// 유효하지 않은 토큰에 대해 401 반환할 Entry Point
@Component
@Slf4j
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    /**
     * 유효하지 않은 토큰으로 접근 시 처리할 Method
     * @param request that resulted in an <code>AuthenticationException</code>
     * @param response so that the user agent can begin authentication
     * @param authException that caused the invocation
     * @throws IOException
     */
    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) throws IOException {
        // 만료된 토큰이라면 request에 저장된 exception 가져오기
        String exception = (String) request.getAttribute("exception");
        log.error("Get Exception : {}", exception);

        if (exception == null) {
            // 만료된 토큰 아니라면 유효하지 않은 토큰
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
        } else if (exception.equals("Expired Token")) {
            // 만료된 토큰 처리
            sendResponse(response);
        }
    }

    /**
     * 만료된 토큰이라는 응답 메시지 보내는 Method
     * @param response
     * @throws IOException
     */
    private void sendResponse(HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);

        response.getWriter().print("Expired Token");
    }
}
