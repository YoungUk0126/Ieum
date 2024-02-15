package com.ukcorp.ieum.api.controller;

import com.ukcorp.ieum.api.service.ChatGPTService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ChatGPT 컨트롤러 생성
 *
 * @author : dachan
 * @fileName : ChatGPTController
 * @since : 01/19/24
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/chatGpt")
public class ChatGPTController {

    private final ChatGPTService chatGPTService;

    /**
     * [API] ChatGPT 모델 리스트를 조회합니다.
     * @return List
     */
    @GetMapping("/modelList")
    public ResponseEntity<Map<String,Object>> selectModelList() {
        try{
          List<Map<String, Object>> result = chatGPTService.modelList();
          return handleSuccess(result);
        }catch (Exception e){
            return handleFail(e.getMessage());
        }
    }

    /**
     * [API] ChatGPT 모델 리스트를 조회합니다.
     * @param : String
     * @return Map
     */
    @PostMapping("/prompt")
    public ResponseEntity<Map<String,Object>> selectPrompt(@RequestBody String message) {
      try{
        String result = chatGPTService.prompt(message);
        return handleSuccess(result);
      }catch (Exception e){
        return handleFail(e.getMessage());
      }
    }

    // Http 전송 템플릿
    private ResponseEntity<Map<String,Object>> handleSuccess(Object data){
      Map<String, Object> result = new HashMap<>();
      result.put("success", true);
      result.put("data", data);
      return new ResponseEntity<Map<String,Object>>(result, HttpStatus.OK);
    }

    private ResponseEntity<Map<String,Object>> handleFail(Object data){
      Map<String, Object> result = new HashMap<>();
      result.put("success", false);
      result.put("data", data);
      return new ResponseEntity<Map<String,Object>>(result, HttpStatus.OK);
    }

}