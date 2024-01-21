package com.ukcorp.ieum.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

// 토큰 생성, 유효성 검사 담당 Class
@Component
@Slf4j
@Getter
public class TokenProvider implements InitializingBean {

    private static final String AUTHORITIES_KEY = "auth";
    private final String SECRET;
    private final long TOKEN_VALIDITY_SECONDS;
    private final long REFRESH_VALIDITY_SECONDS;

    private Key key;

    public TokenProvider(
            @Value("${jwt.secret-key}") String secret,
            @Value("${jwt.expiration}") long tokenValidity,
            @Value("${jwt.refresh.expiration}") long refreshValidity) {
        this.SECRET = secret;
        this.TOKEN_VALIDITY_SECONDS = tokenValidity;
        this.REFRESH_VALIDITY_SECONDS = refreshValidity;
    }

    /**
     * Bean 생성된 후 주입받은 secret값 Base64 decode하여 key변수에 할당
     */
    @Override
    public void afterPropertiesSet() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET);
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }

    /**
     * Authentication 권한 정보로 토큰 생성
     *
     * @param authentication
     * @return
     */
    public String createToken(Authentication authentication, boolean isRefresh) {
        // Authentication 객체의 권한 정보 얻어오기
        String authorities = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        // 토큰 만료 시간 설정
        long now = (new Date()).getTime();
        Date validity = null;
        if (isRefresh) {
            validity = new Date(now + this.REFRESH_VALIDITY_SECONDS);
        } else {
            validity = new Date(now + this.TOKEN_VALIDITY_SECONDS);
        }

        // 토큰 생성하여 반환
        return Jwts.builder()
                .setSubject(authentication.getName())
                .claim(AUTHORITIES_KEY, authorities)
                .signWith(key, SignatureAlgorithm.HS512)
                .setExpiration(validity)
                .compact();
    }

    /**
     * 토큰에 담긴 Authentication 권한 정보 얻기
     *
     * @param token
     * @return
     */
    public Authentication getAuthentication(String token) {
        // 토큰으로 Claim 생성
        Claims claims = Jwts
                .parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();

        // Claim에서 권한 정보 뽑기
        Collection<? extends GrantedAuthority> authorities =
                Arrays.stream(claims.get(AUTHORITIES_KEY).toString().split(","))
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList());

        // 권한 정보로 User 객체 만들기
        User principal = new User(claims.getSubject(), "", authorities);

        // 유저, 토큰, 권한 정보로 Authentication 생성 후 반환
        return new UsernamePasswordAuthenticationToken(principal, token, authorities);
    }

    /**
     * 토큰의 유효성 검사
     *
     * @param token
     * @return
     */
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
            log.info("잘못된 JWT 서명입니다.");
        } catch (ExpiredJwtException e) {
            log.info("만료된 JWT 토큰입니다.");
        } catch (UnsupportedJwtException e) {
            log.info("지원되지 않는 JWT 토큰입니다.");
        } catch (IllegalArgumentException e) {
            log.info("JWT 토큰이 잘못되었습니다.");
        }
        return false;
    }
}
