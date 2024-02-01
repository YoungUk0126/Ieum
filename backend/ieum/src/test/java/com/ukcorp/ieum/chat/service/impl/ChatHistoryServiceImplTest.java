package com.ukcorp.ieum.chat.service.impl;

import com.ukcorp.ieum.care.entity.CareInfo;
import com.ukcorp.ieum.chat.dto.ChatHistoryDto;
import com.ukcorp.ieum.chat.dto.request.ChatHIstoryRequestDto;
import com.ukcorp.ieum.chat.dto.response.ChatHistoryResponseDto;
import com.ukcorp.ieum.chat.entity.ChatHistory;
import com.ukcorp.ieum.chat.repository.ChatHistoryRepository;
import com.ukcorp.ieum.chat.service.ChatHistoryService;
import lombok.RequiredArgsConstructor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ChatHistoryServiceImplTest {

  @Autowired
  private ChatHistoryService chatHistoryService;
  @Autowired
  private ChatHistoryRepository chatHistoryRepository;

//  @Test
//  void 범위_없는_대화_조회() {
//    Pageable pageable = PageRequest.of(0, 10);
//    ChatHIstoryRequestDto chatHIstoryRequestDto = new ChatHIstoryRequestDto();
//    ChatHistoryResponseDto chatHistory = chatHistoryService.getChatHistory(1L, pageable, chatHIstoryRequestDto);
//    Assertions.assertThat(chatHistory.getContents().size()).isEqualTo(6);
//
//    for (ChatHistoryDto content : chatHistory.getContents()) {
//      System.out.println(content.getMessage());
//    }
//
////    List<ChatHistory> histories = chatHistoryRepository.findChatHistoriesByCareInfoCareNo(1L);
////    Assertions.assertThat(histories.size()).isEqualTo(6);
//  }

  @Test
  void 범위_있는_대화_조회() {

//    for (int i = 0; i < 10; i++) {
//      ChatHistory history = ChatHistory.builder()
//              .careInfo(CareInfo.builder()
//                      .careNo(2L)
//                      .build())
//              .messageContent("HI")
//              .build();
//      chatHistoryRepository.save(history);
//    }


//    Pageable pageable = PageRequest.of(0, 100);
//    ChatHIstoryRequestDto chatHIstoryRequestDto = ChatHIstoryRequestDto.builder()
//            .startDate(LocalDateTime.of(2024, 01, 01, 01, 01))
//            .endDate(LocalDateTime.now())
//            .build();
//    ChatHistoryResponseDto chatHistory = chatHistoryService.getChatHistory(2L, pageable, chatHIstoryRequestDto);
//    Assertions.assertThat(chatHistory.getContents().size()).isEqualTo(30);
//
//    for (ChatHistoryDto content : chatHistory.getContents()) {
//      System.out.println(content.getMessage());
//    }

    List<ChatHistory> histories = chatHistoryRepository.findTop8ByCareInfoCareNo(2L);
    Assertions.assertThat(histories.size()).isEqualTo(7);
  }
}