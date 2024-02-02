package com.ukcorp.ieum.chat.service.impl;

import com.ukcorp.ieum.chat.dto.ChatHistoryDto;
import com.ukcorp.ieum.chat.dto.request.ChatHIstoryDto;
import com.ukcorp.ieum.chat.dto.response.ChatHistoryResponseDto;
import com.ukcorp.ieum.chat.entity.ChatHistory;
import com.ukcorp.ieum.chat.mapper.ChatHistoryMapper;
import com.ukcorp.ieum.chat.repository.ChatHistoryRepository;
import com.ukcorp.ieum.chat.service.ChatHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatHistoryServiceImpl implements ChatHistoryService {

  private final ChatHistoryRepository chatHistoryRepository;
  private final ChatHistoryMapper chatHistoryMapper;

  @Override
  public ChatHistoryResponseDto getChatHistory(Long careNo, Pageable pageable, ChatHIstoryDto chatHIstoryDto) {

    //기간검색 안함
    if (chatHIstoryDto.getStartDate() == null) {
      Page<ChatHistory> chatHistories = chatHistoryRepository.findAllByCareInfoCareNoOrderByChatHistoryNoAsc(careNo, pageable);
      List<ChatHistoryDto> chatHistoryDtos = chatHistoryMapper.chatHistoriesToChatHistoryDto(chatHistories.getContent());

      return ChatHistoryResponseDto.builder()
              .contents(chatHistoryDtos)
              .totalElements(chatHistories.getTotalElements())
              .totalPages(chatHistories.getTotalPages())
              .build();
    }
    //기간 검색
    else {
      Page<ChatHistory> chatHistories = chatHistoryRepository.findAllByCareInfoCareNoAndChatDateBetweenOrderByChatHistoryNoAsc(careNo, chatHIstoryDto.getStartDate(), chatHIstoryDto.getEndDate(), pageable);
      List<ChatHistoryDto> chatHistoryDtos = chatHistoryMapper.chatHistoriesToChatHistoryDto(chatHistories.getContent());

      return ChatHistoryResponseDto.builder()
              .contents(chatHistoryDtos)
              .totalElements(chatHistories.getTotalElements())
              .totalPages(chatHistories.getTotalPages())
              .build();
    }
  }

  @Override
  public ChatHistoryResponseDto getChatHistoryLimit(Long careNo) {

    List<ChatHistory> chatHistories = chatHistoryRepository.findTop8ByCareInfoCareNo(careNo);
    List<ChatHistoryDto> chatHistoryDtos = chatHistoryMapper.chatHistoriesToChatHistoryDto(chatHistories);
    return ChatHistoryResponseDto.builder()
            .contents(chatHistoryDtos)
            .totalElements(0)
            .totalPages(0)
            .build();
  }
}
