package com.ukcorp.ieum.api.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ukcorp.ieum.api.config.NaverConfig;
import com.ukcorp.ieum.api.dto.MessageDTO;
import com.ukcorp.ieum.api.dto.SmsRequestDTO;
import com.ukcorp.ieum.api.dto.SmsResponseDTO;
import com.ukcorp.ieum.api.service.NaverService;
import com.ukcorp.ieum.chat.dto.EmotionDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

/**
 * Naver Service 구현체
 *
 * @author : dachan
 * @fileName : NaverServiceImpl
 * @since : 01/20/24
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class NaverServiceImpl implements NaverService {

  // 네이버 API 활용 관련 Config 클래스 주입
  private final NaverConfig naverConfig;

  @Value("${naver-cloud-sms.senderPhone}")
  private String phone;

  @Value("${naver-cloud-sms.serviceId}")
  private String serviceId;

  /**
   * 문자 전송 로직
   *
   * @param messageDto
   * @return SmsResponseDTO
   */
  @Override
  public SmsResponseDTO sendSms(MessageDTO messageDto)
          throws UnsupportedEncodingException, NoSuchAlgorithmException, InvalidKeyException, JsonProcessingException, URISyntaxException {
    log.debug("[+] 메세지 전송 시작");
    Long time = System.currentTimeMillis();
    HttpHeaders headers = naverConfig.getSmsHttpHeaders(time);

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
    SmsResponseDTO response = restTemplate.postForObject(new URI("https://sens.apigw.ntruss.com/sms/v2/services/" + serviceId + "/messages"), httpBody, SmsResponseDTO.class);
    log.debug("[+] 메세지 전송 완료");
    return response;
  }

  /**
   * STT 로직
   *
   * @param file
   * @return String
   */
  @Override
  public String getSTT(MultipartFile file) {
    log.debug("[+] STT 생성 시작");
    try {
      // STT를 위한 Connection 객체 생성
      HttpURLConnection conn = naverConfig.getSttHttpURLConnection();
      
      // 음성 파일을 Byte 단위로 읽어서 outputStream으로 출력
      OutputStream outputStream = conn.getOutputStream();
      File voiceFile = convertMultiPartToFile(file);

      FileInputStream inputStream = new FileInputStream(voiceFile);
      byte[] buffer = new byte[4096];
      int bytesRead = -1;
      while ((bytesRead = inputStream.read(buffer)) != -1) {
        outputStream.write(buffer, 0, bytesRead);
      }
      outputStream.flush();
      outputStream.close();
      inputStream.close();
      
      // HTTP 응답 받기
      BufferedReader br = null;
      int responseCode = conn.getResponseCode();
      if (responseCode == 200) { // 정상 호출
        br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
      } else {  // 오류 발생
        throw new Exception("호출 오류!!");
      }
      
      // response 읽어와서 출력
      String inputLine;
      StringBuilder response = new StringBuilder();
      while ((inputLine = br.readLine()) != null) {
        response.append(inputLine);
      }
      br.close();
      log.debug("[+] STT 성공");
      conn.disconnect();

      return response.toString();
    } catch (Exception e) {
      log.debug("[+] STT 실패\n" + e.getMessage());
      return "Fail";
    }
  }

  @Override
  public String getEmotion(EmotionDto content) {
    log.debug("[+] Naver 감정 분석 시작");
    try {
      // 감정분석을 위한 Connection 객체 생성
      HttpURLConnection conn = naverConfig.getEmotionHttpURLConnection();

      // OutputStream으로 Post 파라미터 전송
      ObjectMapper objectMapper = new ObjectMapper();
      String request = objectMapper.writeValueAsString(content);
      log.info("객체를 string으로 변환한 결과 값 >> " + request);
      try (OutputStream outputStream = conn.getOutputStream()) {
        outputStream.write(request.getBytes(StandardCharsets.UTF_8));
      }

      // HTTP 응답 받기
      int responseCode = conn.getResponseCode();
      log.info("응답 코드 >> " + responseCode);
      if (responseCode == 200) { // 정상 호출
        try (BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
          StringBuilder response = new StringBuilder();
          String inputLine;
          while ((inputLine = br.readLine()) != null) {
            response.append(inputLine);
          }
          log.info("stringbuilder >> " + response.toString());

          // JSON 파싱
          JsonNode rootNode = objectMapper.readTree(response.toString());
          JsonNode sentimentNode = rootNode.path("document").path("sentiment");
          if (!sentimentNode.isMissingNode()) {
            String sentimentValue = sentimentNode.asText();
            log.debug("[+] 감정 분석 성공: " + sentimentValue);
            log.info("[+] 감정 분석 성공: " + sentimentValue);
            return sentimentValue;
          } else {
            log.error("[+] document.sentiment 값을 찾을 수 없습니다.");
            return "Fail";
          }
        }
      } else { // 오류 발생
        throw new Exception("호출 오류!!");
      }
    } catch (Exception e) {
      e.printStackTrace();
      log.error("[+] 감정 분석 실패", e);
      return "Fail";
    }
  }

  /**
   * MultipartFile to File 메서드
   *
   * @param file
   * @return File
   */
  private File convertMultiPartToFile(MultipartFile file) throws Exception {
    try {
      File convertedFile = File.createTempFile("temp", null);
      file.transferTo(convertedFile);
      log.debug("Multipart to File 변환 성공");
      return convertedFile;

    } catch (IOException e) {
      log.debug("Multipart to File 변환 실패");
      throw new Exception("Multipart to File 변환 실패");
    }
  }
}