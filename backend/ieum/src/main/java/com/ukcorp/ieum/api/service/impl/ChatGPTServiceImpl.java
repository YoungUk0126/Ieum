package com.ukcorp.ieum.api.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ukcorp.ieum.api.config.ChatGPTConfig;
import com.ukcorp.ieum.api.dto.ChatGPTRequestDto;
import com.ukcorp.ieum.api.service.ChatGPTService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ChatGPT Service 구현체
 *
 * @author : dachan
 * @fileName : ChatGPTServiceImpl
 * @since : 01/19/24
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ChatGPTServiceImpl implements ChatGPTService {

    private final ChatGPTConfig chatGPTConfig;

    @Value("${openai.model}")
    private String model;

    /**
     * 사용 가능한 모델 리스트를 조회하는 비즈니스 로직
     * @return List
     */
    @Override
    public List<Map<String, Object>> modelList() {
        log.debug("[+] 모델 리스트를 조회합니다.");
        List<Map<String, Object>> resultList = null;

        // [STEP1] 토큰 정보가 포함된 Header를 가져옵니다.
        HttpHeaders headers = chatGPTConfig.httpHeaders();

        // [STEP2] 통신을 위한 RestTemplate을 구성합니다.
        ResponseEntity<String> response = new RestTemplate()
                .exchange(
                        "https://api.openai.com/v1/models",
                        HttpMethod.GET,
                        new HttpEntity<>(headers),
                        String.class);

        try {
            // [STEP3] Jackson을 기반으로 응답값을 가져옵니다.
            ObjectMapper om = new ObjectMapper();
            Map<String, Object> data = om.readValue(response.getBody(), new TypeReference<Map<String, Object>>() {});

            // [STEP4] 응답 값을 결과값에 넣고 출력을 해봅니다.
            resultList = (List<Map<String, Object>>) data.get("data");
            for (Map<String, Object> object : resultList) {
                log.debug("ID: " + object.get("id"));
                log.debug("Object: " + object.get("Object"));
                log.debug("Created: " + object.get("created"));
                log.debug("Owned By: " + object.get("owned_by"));
            }
        } catch (JsonProcessingException e) {
            log.debug("JsonProcessingException :: " + e.getMessage());
        }
        return resultList;
    }

    /**
     * ChatGTP 프롬프트 검색
     *
     * @param message
     * @return
     */
    @Override
    public Map<String, Object> prompt(String message) {
        log.debug("[+] 프롬프트를 수행합니다.");

        Map<String, Object> result = new HashMap<>();

        // [STEP1] 토큰 정보가 포함된 Header를 가져옵니다.
        HttpHeaders headers = chatGPTConfig.httpHeaders();

        // [STEP2] 프롬프트 전송을 위한 메세지 템플릿을 생성합니다.
        String requestBody = "";
        ObjectMapper om = new ObjectMapper();

        List<Map<String, Object>> messages = new ArrayList<>();

        // 첫 번째 메시지 - 시스템 역할 지정
        Map<String, Object> systemMessage = new HashMap<>();
        systemMessage.put("role", "system");
        systemMessage.put("content", "안녕하세요, 할아버지! 저는 10살 어린이 이음이라고해요. 어떻게 도와드릴까요?");
        messages.add(systemMessage);

        // 두 번째 메시지 - 사용자 메시지 전달
        Map<String, Object> userMessage = new HashMap<>();
        userMessage.put("role", "user");
        userMessage.put("content", message);
        messages.add(userMessage);

        // 세 번째 메시지 - 어시스턴트 메시지
        Map<String, Object> assistantMessage = new HashMap<>();
        assistantMessage.put("role", "assistant");
        assistantMessage.put("content", "네, 할아버지! 제가 도와줄게요. 궁금한 게 있으면 물어봐주세요!");
        messages.add(assistantMessage);


        // [STEP3] properties의 model을 가져와서 객체에 추가합니다.
        ChatGPTRequestDto completionRequestDto = ChatGPTRequestDto.builder()
                .model(model)
                .messages(messages)
                .temperature(0.8f)
                .build();

        try {
            // [STEP4] Object -> String 직렬화를 구성합니다.
            requestBody = om.writeValueAsString(completionRequestDto);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        // [STEP5] 통신을 위한 RestTemplate을 구성합니다.
        HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);
        ResponseEntity<String> response = new RestTemplate()
                .exchange(
                        "https://api.openai.com/v1/chat/completions",
                        HttpMethod.POST,
                        requestEntity,
                        String.class);
        try {
            // [STEP6] String -> HashMap 역직렬화를 구성합니다.
            result = om.readValue(response.getBody(), new TypeReference<Map<String, Object>>() {});
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        log.debug("[+] 프롬프트 답변이 생성됐습니다" + result);
        return result;
    }
}