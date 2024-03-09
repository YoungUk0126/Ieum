package com.ukcorp.ieum.chat.controller;

import com.ukcorp.ieum.chat.dto.request.ChatHistoryRequestDto;
import com.ukcorp.ieum.chat.dto.response.ChatHistoryResponseDto;
import com.ukcorp.ieum.chat.service.ChatHistoryService;
import com.ukcorp.ieum.jwt.JwtUtil;
import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/chat")
@RequiredArgsConstructor
public class ChatHistoryController {

  private final ChatHistoryService chatHistoryService;

  @GetMapping
  public ResponseEntity getChatHistory( ChatHistoryRequestDto chatHIstoryDto, @PageableDefault(page = 0) Pageable pageable) {
    Long careNo = JwtUtil.getCareNo().get();

    ChatHistoryResponseDto result = chatHistoryService.getChatHistory(careNo, pageable, chatHIstoryDto.toDto());
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
