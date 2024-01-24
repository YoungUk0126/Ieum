package com.ukcorp.ieum.jwt;

import com.ukcorp.ieum.exception.ExpiredTokenException;
import com.ukcorp.ieum.exception.InvalidRefreshTokenException;
import com.ukcorp.ieum.jwt.dto.JwtToken;
import com.ukcorp.ieum.member.repository.MemberRepository;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

// 토큰 생성, 유효성 검사 담당 Class
@Component
@Slf4j
@Getter
@RequiredArgsConstructor
public class TokenProvider implements InitializingBean {
    private final RedisTemplate<String, String> redisTemplate;
    private final MemberRepository memberRepository;

    private static final String AUTHORITIES_KEY = "auth";
    @Value("${jwt.secret-key}")
    private String SECRET;
    @Value("${jwt.expiration}")
    private long ACCESS_VALIDITY_SECONDS;
    @Value("${jwt.refresh.expiration}")
    private long REFRESH_VALIDITY_SECONDS;

    private Key key;


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
    public JwtToken createToken(Authentication authentication) {
        // Authentication 객체의 권한 정보 얻어오기
        String authorities = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        // 토큰 생성
        String accessToken = createAccessToken(authentication, authorities);
        String refreshToken = createRefreshToken(authentication, authorities);

        return JwtToken.builder()
                .grantType("Bearer")
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    /**
     * AccessToken 생성 Method
     *
     * @param authentication
     * @return
     */
    private String createAccessToken(Authentication authentication, String authorities) {
        long now = (new Date()).getTime();
        Date validity = new Date(now + this.ACCESS_VALIDITY_SECONDS);

        return Jwts.builder()
                .setSubject(authentication.getName())
                .claim(AUTHORITIES_KEY, authorities)
                .signWith(key, SignatureAlgorithm.HS512)
                .setExpiration(validity)
                .compact();
    }

    /**
     * RefreshToken 생성 Method
     *
     * @param authentication
     * @return
     */
    private String createRefreshToken(Authentication authentication, String authorities) {
        long now = (new Date()).getTime();
        Date validity = new Date(now + this.REFRESH_VALIDITY_SECONDS);

        // Refresh Token 생성
        String refreshToken = Jwts.builder()
                .setSubject(authentication.getName())
                .claim(AUTHORITIES_KEY, authorities)
                .signWith(key, SignatureAlgorithm.HS512)
                .setExpiration(validity)
                .compact();

        // Redis에 RefreshToken 저장
        redisTemplate.opsForValue().set(
                authentication.getName(),
                refreshToken,
                REFRESH_VALIDITY_SECONDS,
                TimeUnit.MILLISECONDS
        );

        return refreshToken;
    }

    /**
     * RefreshToken을 이용하여 AccessToken 재발급 받는 Method
     *
     * @param refreshToken
     * @return accessToken
     */
    public JwtToken refreshAccessToken(String refreshToken, String memberId, Authentication authentication) {

        // Redis에서 저장된 RefreshToken 가져오기
        String storedRefreshToken = redisTemplate.opsForValue().get(memberId);

        // 저장된 RefreshToken과 들어온 RefreshToken이 일치하면 새로운 AccessToken 생성
        if (storedRefreshToken != null && storedRefreshToken.equals(refreshToken)) {
            // 데이터베이스에서 사용자 정보 가져오기

            // 기존 AccessToken의 권한 정보를 그대로 사용하여 새로운 AccessToken 생성
            return createToken(authentication);

        } else {
            // Redis에 있는 Refresh Token 아니라면 InvalidRefreshTokenException
            throw new InvalidRefreshTokenException("Invalid Refresh Token");
        }

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
     * RefreshToken에서 유저 정보 얻기
     *
     * @param refreshToken
     * @return memberId
     */
    public String getMemberId(String refreshToken) {
        try {
            // RefreshToken에서 claims 뽑아오기
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(key).build()
                    .parseClaimsJws(refreshToken)
                    .getBody();

            // Claims에 저장된 memberId 가져오기
            return Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(refreshToken)
                    .getBody()
                    .getSubject();
        } catch (Exception e) {
            throw new InvalidRefreshTokenException("Invalid Token Exception");
        }
    }

    /**
     * 로그아웃 시 Redis에서 RefreshToken 삭제
     * @param memberId
     */
    public void deleteRefreshTokenFromRedis(String memberId) {
        redisTemplate.delete(memberId);
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
            throw new ExpiredTokenException("Expired Token");
        } catch (UnsupportedJwtException e) {
            log.info("지원되지 않는 JWT 토큰입니다.");
        } catch (IllegalArgumentException e) {
            log.info("JWT 토큰이 잘못되었습니다.");
        }
        return false;
    }
}
