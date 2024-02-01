package com.ukcorp.ieum.chat.controller;

import com.ukcorp.ieum.chat.dto.request.ChatHIstoryRequestDto;
import com.ukcorp.ieum.chat.dto.response.ChatHistoryResponseDto;
import com.ukcorp.ieum.chat.service.ChatHistoryService;
import com.ukcorp.ieum.jwt.JwtUtil;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/api/messages")
@RequiredArgsConstructor
public class ChatHistoryController {

  private final ChatHistoryService chatHistoryService;

  @GetMapping
  public ResponseEntity getChatHistory(ChatHIstoryRequestDto chatHIstoryRequestDto, @PageableDefault(page = 0) Pageable pageable) {
    Long careNo = JwtUtil.getCareNo().get();
    ChatHistoryResponseDto result = chatHistoryService.getChatHistory(careNo, pageable, chatHIstoryRequestDto);
    return handleSuccess(result);
  }

  @GetMapping("/limit")
  public ResponseEntity getChatHistoryLimit() {
    Long careNo = JwtUtil.getCareNo().get();
    ChatHistoryResponseDto result = chatHistoryService.getChatHistoryLimit(careNo);
    return handleSuccess(result);
  }

  private ResponseEntity<Map<String, Object>> handleSuccess(Object data) {
    Map<String, Object> result = new HashMap<>();

    result.put("success", true);
    result.put("data", data);
    return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
  }

  private ResponseEntity<Map<String, Object>> handleError(Object data) {
    Map<String, Object> result = new HashMap<>();
    result.put("success", false);
    result.put("data", data);
    return new ResponseEntity<Map<String, Object>>(result, HttpStatus.BAD_REQUEST);
  }
}
