package com.ukcorp.ieum.chat.service;

import com.ukcorp.ieum.chat.dto.request.ChatHIstoryDto;
import com.ukcorp.ieum.chat.dto.response.ChatHistoryResponseDto;
import org.springframework.data.domain.Pageable;

public interface ChatHistoryService {


  ChatHistoryResponseDto getChatHistory(Long careNo, Pageable pageable, ChatHIstoryDto chatHIstoryDto);

  ChatHistoryResponseDto getChatHistoryLimit(Long careNo);
}
