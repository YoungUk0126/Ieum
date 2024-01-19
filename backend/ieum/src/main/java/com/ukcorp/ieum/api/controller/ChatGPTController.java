package com.ukcorp.ieum.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * ChatGPT에서 사용하는 환경 구성
 *
 * @author : dachan
 * @fileName : ChatGPTConfig
 * @since : 01/19/24
 */
@RestController
@RequestMapping(value = "/api/chatGpt")
public class ChatGPTController {

    private final ChatGPTService chatGPTService;

    public ChatGPTController(ChatGPTService chatGPTService) {
        this.chatGPTService = chatGPTService;
    }

    /**
     * [API] ChatGPT 모델 리스트를 조회합니다.
     * @return List
     */
    @GetMapping("/modelList")
    public ResponseEntity<List<Map<String, Object>>> selectModelList() {
        List<Map<String, Object>> result = chatGPTService.modelList();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    /**
     * [API] ChatGPT 모델 리스트를 조회합니다.
     * @param : String
     * @return Map
     */
    @PostMapping("/prompt")
    public ResponseEntity<Map<String, Object>> selectPrompt(@RequestBody String message) {
        Map<String, Object> result = chatGPTService.prompt(message);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}