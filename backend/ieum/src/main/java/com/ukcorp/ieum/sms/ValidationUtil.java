package com.ukcorp.ieum.sms;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;

@Component
@Slf4j
public class ValidationUtil {

    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final int CODE_LENGTH = 6;


    @Autowired
    public ValidationUtil(@Qualifier("redisTemplate") RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    private final RedisTemplate<String, String> redisTemplate;


    /**
     * 문자로 받은 코드 인증
     *
     * @param phone
     * @param code
     * @return 인증 여부
     */
    public boolean verifyCode(String phone, String code) {
        // Redis에 해당 핸드폰번호로 코드 존재하는지 확인
        if (!redisTemplate.hasKey(phone) || !redisTemplate.opsForValue().get(phone).equals(code)) {
            return false;
        }

        // 인증번호 맞을 경우 Redis에서 삭제
        redisTemplate.delete(phone);
        return true;
    }

    /**
     * 랜덤 코드 생성
     *
     * @return 6자리 랜덤코드
     */
    public String createRandomCode() {
        SecureRandom random = new SecureRandom();
        StringBuilder code = new StringBuilder();

        for (int i = 0; i < CODE_LENGTH; i++) {
            int randomIndex = random.nextInt(CHARACTERS.length());
            code.append(CHARACTERS.charAt(randomIndex));
        }

        return code.toString();
    }
}