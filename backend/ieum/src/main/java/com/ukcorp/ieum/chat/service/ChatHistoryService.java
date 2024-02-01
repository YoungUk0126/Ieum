package com.ukcorp.ieum.chat.service;

import com.ukcorp.ieum.chat.dto.request.ChatHIstoryRequestDto;
import com.ukcorp.ieum.chat.dto.response.ChatHistoryResponseDto;
import org.springframework.data.domain.Pageable;

public interface ChatHistoryService {


  ChatHistoryResponseDto getChatHistory(Long careNo, Pageable pageable, ChatHIstoryRequestDto chatHIstoryRequestDto);

  ChatHistoryResponseDto getChatHistoryLimit(Long careNo);
}
