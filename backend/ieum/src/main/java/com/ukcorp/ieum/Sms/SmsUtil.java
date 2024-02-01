package com.ukcorp.ieum.Sms;

import com.ukcorp.ieum.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.nurigo.sdk.NurigoApp;
import net.nurigo.sdk.message.model.Message;
import net.nurigo.sdk.message.request.SingleMessageSendingRequest;
import net.nurigo.sdk.message.response.SingleMessageSentResponse;
import net.nurigo.sdk.message.service.DefaultMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.security.SecureRandom;
import java.time.Duration;

@Component
@Slf4j
public class SmsUtil {
    @Value("${coolsms.api.key}")
    private String apiKey;
    @Value("${coolsms.api.secret}")
    private String apiSecretKey;

    @Value(("${coolsms.api.fromNumber}"))
    private String fromNumber;

    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final int CODE_LENGTH = 6;

    private DefaultMessageService messageService;

    @Autowired
    public SmsUtil(@Qualifier("redisTemplate") RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    private final RedisTemplate<String, String> redisTemplate;

    @PostConstruct
    private void init() {
        this.messageService = NurigoApp.INSTANCE
                .initialize(apiKey, apiSecretKey, "https://api.coolsms.co.kr");
    }

    /**
     * coolSms를 이용한 메시지 발송
     *
     * @param phone
     * @return
     */
    public SingleMessageSentResponse sendOne(String phone) {
        Message message = new Message();
        String code = createRandomCode();
        log.info("보내는 번호 " + phone + "  코드 >> " + code);
        message.setFrom(fromNumber);
        message.setTo(phone);
        message.setText("[이음] 아래의 인증번호를 입력해주세요\n" + code);

        // 핸드폰번호 + 코드 Redis에 저장
        redisTemplate.opsForValue()
                .set(phone, code, Duration.ofSeconds(3 * 60));

        return this.messageService.sendOne(new SingleMessageSendingRequest(message));
    }

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
    private String createRandomCode() {
        SecureRandom random = new SecureRandom();
        StringBuilder code = new StringBuilder();

        for (int i = 0; i < CODE_LENGTH; i++) {
            int randomIndex = random.nextInt(CHARACTERS.length());
            code.append(CHARACTERS.charAt(randomIndex));
        }

        return code.toString();
    }
}