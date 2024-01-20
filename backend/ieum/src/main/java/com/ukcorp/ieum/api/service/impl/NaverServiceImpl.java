package com.ukcorp.ieum.api.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ukcorp.ieum.api.dto.MessageDTO;
import com.ukcorp.ieum.api.dto.SmsRequestDTO;
import com.ukcorp.ieum.api.dto.SmsResponseDTO;
import com.ukcorp.ieum.api.service.NaverService;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
/**
 * Naver Service 구현체
 *
 * @author : dachan
 * @fileName : NaverServiceImpl
 * @since : 01/20/24
 */
@Primary
@Service
@Slf4j
public class NaverServiceImpl implements NaverService {

  @Value("${naver-cloud-sms.accessKey}")
  private String accessKey;

  @Value("${naver-cloud-sms.secretKey}")
  private String secretKey;

  @Value("${naver-cloud-sms.serviceId}")
  private String serviceId;

  @Value("${naver-cloud-sms.senderPhone}")
  private String phone;

  /**
   * 문자 전송 로직
   * @param messageDto
   * @return SmsResponseDTO
   */
  @Override
  public SmsResponseDTO sendSms(MessageDTO messageDto )
      throws UnsupportedEncodingException, NoSuchAlgorithmException, InvalidKeyException, JsonProcessingException, URISyntaxException {
    log.debug("[+] 메세지 전송 시작");

    Long time = System.currentTimeMillis();
    // 네이버에 전송하게 될 헤더 값
    // 헤더 설정시 주의 사항은 time을 설정할 때 header에 들어가는 시간과 signature에 들어가는 시간이 동일한 값을 가지고 있어야 한다.
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    headers.set("x-ncp-apigw-timestamp", time.toString());
    headers.set("x-ncp-iam-access-key", accessKey);
    headers.set("x-ncp-apigw-signature-v2", makeSignature(time));

    List<MessageDTO> messages = new ArrayList<>();
    messages.add(messageDto);

    // Smsrequest에는 아래와 같은 정보가 들어가야 함
    SmsRequestDTO request = SmsRequestDTO.builder()
        .type("SMS")
        .contentType("COMM")
        .countryCode("82")
        .from(phone)
        .content(messageDto.getContent())
        .messages(messages)
        .build();

    // ObjectMapper를 통해서 request값을 body에 매핑시켜주기
    ObjectMapper objectMapper = new ObjectMapper();
    String body = objectMapper.writeValueAsString(request);
    HttpEntity<String> httpBody = new HttpEntity<>(body, headers);

    // RestTemplate을 만들어서 네이버로 reqeust를 보내면 문자가 전송되고 이후 SmsResponse가 들어온다.
    RestTemplate restTemplate = new RestTemplate();
    restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory());
    SmsResponseDTO response = restTemplate.postForObject(new URI("https://sens.apigw.ntruss.com/sms/v2/services/"+ serviceId +"/messages"), httpBody, SmsResponseDTO.class);
    log.debug("[+] 메세지 전송 완료");
    return response;
  };
  
  /**
   * 문자 전송을 위해 필요한 시그니처 생성 로직
   * @param time
   * @return String
   */
  public String makeSignature(Long time)
      throws NoSuchAlgorithmException, InvalidKeyException, UnsupportedEncodingException {

    // signature에서 사용하게 될 구분자와 HTTP 방식, 앱키등을 등록해서 인코딩된 값으로 시그니쳐를 생성하기
    String space = " ";
    String newLine = "\n";
    String method = "POST";
    String url = "/sms/v2/services/"+ this.serviceId+"/messages";
    String timestamp = time.toString();
    String accessKey = this.accessKey;
    String secretKey = this.secretKey;

    String message = new StringBuilder()
        .append(method)
        .append(space)
        .append(url)
        .append(newLine)
        .append(timestamp)
        .append(newLine)
        .append(accessKey)
        .toString();

    SecretKeySpec signingKey = new SecretKeySpec(secretKey.getBytes("UTF-8"), "HmacSHA256");
    Mac mac = Mac.getInstance("HmacSHA256");
    mac.init(signingKey);

    byte[] rawHmac = mac.doFinal(message.getBytes("UTF-8"));
    String encodeBase64String = Base64.encodeBase64String(rawHmac);

    return encodeBase64String;
  }

}