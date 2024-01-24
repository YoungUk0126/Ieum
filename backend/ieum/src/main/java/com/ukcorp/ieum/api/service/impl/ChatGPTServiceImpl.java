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
    public String prompt(String message) {
        log.debug("[+] 프롬프트를 수행합니다.");

        Map<String, Object> result = new HashMap<>();

        // [STEP1] 토큰 정보가 포함된 Header를 가져옵니다.
        HttpHeaders headers = chatGPTConfig.httpHeaders();

        // [STEP2] 프롬프트 전송을 위한 메세지 템플릿을 생성합니다.
        String requestBody = "";
        ObjectMapper om = new ObjectMapper();

        List<Map<String, Object>> messages = new ArrayList<>();

        // 첫 번째 메시지 - 시스템 역할 지정 (어린이 역할)
        Map<String, Object> childSystemMessage = new HashMap<>();
        childSystemMessage.put("role", "system");
        childSystemMessage.put("content", "너는 10살 어린이 이음이야. 비속어를 사용하지 않고 답변은 한국어로해. 말투, 지능, 답변은 10살 어린아이에 맞게 해야해. 답변은 예의있고 친근한 말투를 사용해야하고 높임말을 써줘. 답변은 한두줄로 한번만 대답을 하고 긍정적인 대답을 해야해. 나는 너의 할아버지야.");
        messages.add(childSystemMessage);

        // 두 번째 메시지 - 사용자 메시지 전달 (할아버지 역할)
        Map<String, Object> grandpaUserMessage = new HashMap<>();
        grandpaUserMessage.put("role", "user");
        grandpaUserMessage.put("content", message);
        messages.add(grandpaUserMessage);

//        // 세 번째 메시지 - 어시스턴트 메시지 (어린이 역할)
//        Map<String, Object> childAssistantMessage = new HashMap<>();
//        childAssistantMessage.put("role", "assistant");
//        childAssistantMessage.put("content", "안녕 할아버지! 이음이가 도와줄 수 있는 건 무엇인가요?");
//        messages.add(childAssistantMessage);


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

        ArrayList<Object> choices = (ArrayList<Object>) result.get("choices");
        Map<String, Object> choice = (Map<String, Object>) choices.get(0);
        Map<String, Object> choicesMessage = (Map<String, Object>) choice.get("message");
      return (String) choicesMessage.get("content");
    }
}