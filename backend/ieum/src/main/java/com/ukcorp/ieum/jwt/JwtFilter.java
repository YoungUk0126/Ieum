package com.ukcorp.ieum.jwt;

import com.ukcorp.ieum.exception.ExpiredTokenException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;

// Custom Filter
@Slf4j
@AllArgsConstructor
public class JwtFilter extends GenericFilterBean {
    public static final String ACCESS_TOKEN_HEADER = "Authorization";
    public static final String REFRESH_TOKEN_HEADER = "Authorization-refresh";
    private final TokenProvider tokenProvider;

    /**
     * JWT 토큰 인증정보 SecurityContext에 저장
     *
     * @param servletRequest  The request to process
     * @param servletResponse The response associated with the request
     * @param filterChain     Provides access to the next filter in the chain for this filter to pass the request and response
     *                        to for further processing
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        // Header에서 토큰 정보 받아오기
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        String accessToken = resolveToken(httpServletRequest);
        String requestURI = httpServletRequest.getRequestURI();

        try {
            // 토큰 유효성 검사 진행
            if (StringUtils.hasText(accessToken) && tokenProvider.validateToken(accessToken)) {
                // 토큰이 정상적이라면 Authentication 객체 받아오기
                Authentication authentication = tokenProvider.getAuthentication(accessToken);
                // 받아온 Authentication 객체 SecurityContext에 저장
                SecurityContextHolder.getContext().setAuthentication(authentication);
                log.debug("Security Context에 '{}' 인증 정보를 저장했습니다, uri: {}", authentication.getName(), requestURI);
            } else {
                log.debug("유효한 JWT 토큰 또는 REFRESH 토큰이 없습니다, uri: {}", requestURI);
            }
        } catch (ExpiredTokenException e) {
            // 만료된 토큰이라면 ExpiredTokenException을 catch
            httpServletRequest.setAttribute("exception", "Expired Token");
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }


    /**
     * Request Header에서 토큰 정보 꺼내오는 Method
     *
     * @param request
     * @return
     */
    private String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader(ACCESS_TOKEN_HEADER);

        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }

        return null;
    }

    /**
     * Request Header에서 Refresh 토큰 정보 꺼내오는 Method
     *
     * @param request
     * @return
     */
    private String resolveRefreshToken(HttpServletRequest request) {
        String refreshToken = request.getHeader(REFRESH_TOKEN_HEADER);

        if (StringUtils.hasText(refreshToken) && refreshToken.startsWith("Bearer ")) {
            return refreshToken.substring(7);
        }

        return null;
    }
}
