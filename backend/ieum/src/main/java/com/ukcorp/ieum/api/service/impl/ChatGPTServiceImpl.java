package com.ukcorp.ieum.api.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ukcorp.ieum.api.config.ChatGPTConfig;
import com.ukcorp.ieum.api.dto.ChatGPTRequestDto;
import com.ukcorp.ieum.api.service.ChatGPTService;

import com.ukcorp.ieum.care.entity.CareInfo;
import com.ukcorp.ieum.care.entity.Gender;
import com.ukcorp.ieum.care.repository.CareRepository;
import com.ukcorp.ieum.chat.entity.ChatHistory;
import com.ukcorp.ieum.chat.entity.Subject;
import com.ukcorp.ieum.chat.repository.ChatHistoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

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
    private final ChatHistoryRepository chatHistoryRepository;
    private final CareRepository careRepository;

    @Value("${openai.model}")
    private String model;

    /**
     * 사용 가능한 모델 리스트를 조회하는 비즈니스 로직
     *
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
            Map<String, Object> data = om.readValue(response.getBody(), new TypeReference<Map<String, Object>>() {
            });

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
     * chatGPT 프롬프트
     *
     * @param message
     * @param careNo
     * @return
     */
    @Override
    public String prompt(String message, Long careNo) {
        log.debug("[+] 프롬프트를 수행합니다.");

        Map<String, Object> result = new HashMap<>();

        // [STEP1] 토큰 정보가 포함된 Header를 가져옵니다.
        HttpHeaders headers = chatGPTConfig.httpHeaders();

        // [STEP2] 프롬프트 전송을 위한 메세지 템플릿을 생성합니다.
        String requestBody = "";
        ObjectMapper om = new ObjectMapper();

        List<Map<String, Object>> messages = getMaps(message, careNo);

        // [STEP3] properties의 model을 가져와서 객체에 추가합니다.
        ChatGPTRequestDto completionRequestDto = ChatGPTRequestDto.builder()
                .model(model)
                .messages(messages)
                .temperature(0.5f)
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
            result = om.readValue(response.getBody(), new TypeReference<Map<String, Object>>() {
            });
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        log.debug("[+] 프롬프트 답변이 생성됐습니다" + result);

        ArrayList<Object> choices = (ArrayList<Object>) result.get("choices");
        Map<String, Object> choice = (Map<String, Object>) choices.get(0);
        Map<String, Object> choicesMessage = (Map<String, Object>) choice.get("message");
        return (String) choicesMessage.get("content");
    }

    private List<Map<String, Object>> getMaps(String message, Long careNo) {
        List<Map<String, Object>> messages = new ArrayList<>();
        CareInfo careInfo = careRepository.findCareInfoByCareNo(careNo)
                .orElseThrow(() -> new NoSuchElementException("NOT FOUND CARE INFO"));
        String gender = (careInfo.getCareGender() == Gender.FEMALE ? "할머니" : "할아버지");

        // 교육 메시지 추가
        addTrainingMessage(messages, "user",
                "너는 10살 어린이 이음이야. 비속어를 사용하지 않고 답변은 한국어로해. " +
                        "말투, 지능, 답변은 너가 10살 어린아이인것처럼 얘기해야해. " +
                        "주의해야할 점은 내가 너의 "+ gender + "라는거야. " +
                        "답변은 예의있고 친근한 말투를 사용해야하고 높임말을 써줘. " +
                        "답변은 한두줄로 한번만 대답을 하고 긍정적인 대답을 해야해. " +
                        "나는 너의 할아버지야. 안녕하세요라는 말은 하지 말고 " +
                        "AI나 어시스턴스같이 어린아이가 하지 않을만한 내용은 말하지마");
        addTrainingMessage(messages, "assistant",
                "네 알겠습니다" + gender+ "!!. " + gender + " 오늘은 뭐하실거에요??");

        // 상황 없이 일반적인 메시지
        addTrainingMessage(messages, "user",
                "이? 하긴 뭘해 집에 그냥 있을거야");
        addTrainingMessage(messages, "assistant",
                "그럼 이음이랑 놀아주세요 " + gender +"!! 식사는 하셨나요?");

        addTrainingMessage(messages, "user",
                "아직. 요즘은 입맛도 없어");
        addTrainingMessage(messages, "assistant",
                "그래도 식사는 거르시면 안돼요! " + gender+"가 식사를 거르시면 이음이 슬퍼요!");

        // 최근 대화목록 추가
        addChatHistory(messages, careNo);

        // 질문 메시지 추가
        addQuestionMessage(messages, "user", message);

        return messages;
    }

    private void addTrainingMessage(List<Map<String, Object>> messages, String role, String content) {
        Map<String, Object> messageMap = new HashMap<>();
        messageMap.put("role", role);
        messageMap.put("content", content);
        messages.add(messageMap);
    }

    private void addChatHistory(List<Map<String, Object>> messages, Long careNo) {
        List<ChatHistory> chatHistory = chatHistoryRepository.findTop8ByCareInfoCareNo(careNo);
        for (ChatHistory chat : chatHistory) {
            String role = (chat.getSpeaker() == Subject.AI) ? "assistant" : "user";
            addTrainingMessage(messages, role, chat.getMessageContent());
        }
    }

    private void addQuestionMessage(List<Map<String, Object>> messages, String role, String content) {
        Map<String, Object> messageMap = new HashMap<>();
        messageMap.put("role", role);
        messageMap.put("content", content);
        messages.add(messageMap);
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

        List<Map<String, Object>> messages = getMaps(message);


        // [STEP3] properties의 model을 가져와서 객체에 추가합니다.
        ChatGPTRequestDto completionRequestDto = ChatGPTRequestDto.builder()
                .model(model)
                .messages(messages)
                .temperature(0.5f)
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
            result = om.readValue(response.getBody(), new TypeReference<Map<String, Object>>() {
            });
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        log.debug("[+] 프롬프트 답변이 생성됐습니다" + result);

        ArrayList<Object> choices = (ArrayList<Object>) result.get("choices");
        Map<String, Object> choice = (Map<String, Object>) choices.get(0);
        Map<String, Object> choicesMessage = (Map<String, Object>) choice.get("message");
        return (String) choicesMessage.get("content");
    }

    private static List<Map<String, Object>> getMaps(String message) {
        List<Map<String, Object>> messages = new ArrayList<>();

        // 첫 번째 교육
        Map<String, Object> grandpaUserMessage = new HashMap<>();
        grandpaUserMessage.put("role", "user");
        grandpaUserMessage.put("content", "너는 10살 어린이 이음이야. 비속어를 사용하지 않고 답변은 한국어로해. 말투, 지능, 답변은 너가 10살 어린아이인것처럼 얘기해야해. 주의해야할 점은 내가 너의 할아버지라는거야. 답변은 예의있고 친근한 말투를 사용해야하고 높임말을 써줘. 답변은 한두줄로 한번만 대답을 하고 긍정적인 대답을 해야해. 나는 너의 할아버지야. 안녕하세요라는 말은 하지 말고 AI나 어시스턴스같이 어린아이가 하지 않을만한 내용은 말하지마");
        messages.add(grandpaUserMessage);

        Map<String, Object> childSystemMessage = new HashMap<>();
        childSystemMessage.put("role", "assistant");
        childSystemMessage.put("content", "네 알겠습니다 할아버지!!. 할아버지 오늘은 뭐하실거에요??");
        messages.add(childSystemMessage);

        // 두 번째 교육
        Map<String, Object> grandpaUserMessage2 = new HashMap<>();
        grandpaUserMessage2.put("role", "user");
        grandpaUserMessage2.put("content", "할애비는 오늘 경로당에 갔다 오려구~~ 오늘 경식이 이놈이랑 바둑두기로 했거든");
        messages.add(grandpaUserMessage2);


        Map<String, Object> childSystemMessage2 = new HashMap<>();
        childSystemMessage2.put("role", "assistant");
        childSystemMessage2.put("content", "친구랑 노는건 재밌을것 같아요!! 경식 할아버지랑은 친하세요??");
        messages.add(childSystemMessage2);

        // 세 번째 교육
        Map<String, Object> grandpaUserMessage3 = new HashMap<>();
        grandpaUserMessage3.put("role", "user");
        grandpaUserMessage3.put("content", "한 10년 정도 같이 지냈으니 당연히 친하지 이눔아!!");
        messages.add(grandpaUserMessage3);


        Map<String, Object> childSystemMessage3 = new HashMap<>();
        childSystemMessage3.put("role", "assistant");
        childSystemMessage3.put("content", "우와 저도 친구들이 많았으면 좋겠어요. 할아버지 부러워요");
        messages.add(childSystemMessage3);

        // 질문
        Map<String, Object> childAssistantMessage = new HashMap<>();
        childAssistantMessage.put("role", "user");
        childAssistantMessage.put("content", message);
        messages.add(childAssistantMessage);
        return messages;
    }
}