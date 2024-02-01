package com.ukcorp.ieum.chat.service.impl;

import com.ukcorp.ieum.chat.dto.request.ChatHIstoryRequestDto;
import com.ukcorp.ieum.chat.dto.response.ChatHistoryResponseDto;
import com.ukcorp.ieum.chat.entity.ChatHistory;
import com.ukcorp.ieum.chat.repository.ChatHistoryRepository;
import com.ukcorp.ieum.chat.service.ChatHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChatHistoryServiceImpl implements ChatHistoryService {

  private final ChatHistoryRepository chatHistoryRepository;

  @Override
  public ChatHistoryResponseDto getChatHistory(Long careNo, Pageable pageable, ChatHIstoryRequestDto chatHIstoryRequestDto) {

    //기간검색 안함
    if (chatHIstoryRequestDto.getStartDate() == null) {
      Page<ChatHistory> chatHistories = chatHistoryRepository.findAllByCareNoOrderByChatHistoryNoAsc(careNo, pageable);

    }
    //기간 검색
    else {

    }

    return null;
  }
}
