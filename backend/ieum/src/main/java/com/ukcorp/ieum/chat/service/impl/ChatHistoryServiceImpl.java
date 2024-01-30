package com.ukcorp.ieum.chat.service.impl;

import com.ukcorp.ieum.chat.dto.request.ChatHIstoryRequestDto;
import com.ukcorp.ieum.chat.service.ChatHistoryService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ChatHistoryServiceImpl implements ChatHistoryService {
  @Override
  public void getChatHistory(Long careNo, Pageable pageable, ChatHIstoryRequestDto chatHIstoryRequestDto) {\

  }
}
