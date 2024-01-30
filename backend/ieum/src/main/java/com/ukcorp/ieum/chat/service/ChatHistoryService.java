package com.ukcorp.ieum.chat.service;

import com.ukcorp.ieum.chat.dto.request.ChatHIstoryRequestDto;
import org.springframework.data.domain.Pageable;

public interface ChatHistoryService {


  void getChatHistory(Long careNo, Pageable pageable, ChatHIstoryRequestDto chatHIstoryRequestDto);
}
