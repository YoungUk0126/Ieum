package com.ukcorp.ieum.chat.controller;

import com.ukcorp.ieum.chat.dto.request.ChatHIstoryRequestDto;
import com.ukcorp.ieum.chat.service.ChatHistoryService;
import com.ukcorp.ieum.jwt.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/messages")
@RequiredArgsConstructor
public class ChatHistoryController {

  private final ChatHistoryService chatHistoryService;

  @GetMapping
  public ResponseEntity getChatHistory(ChatHIstoryRequestDto chatHIstoryRequestDto, @PageableDefault(page = 1) Pageable pageable) {
    Long careNo = JwtUtil.getCareNo().get();
//    chatHistoryService.getChatHistory(careNo, pageable, chatHIstoryRequestDto);

    return new ResponseEntity(HttpStatus.OK);
  }
}
