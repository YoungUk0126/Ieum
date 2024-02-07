package com.ukcorp.ieum.chat.service.impl;

import com.ukcorp.ieum.care.entity.CareInfo;
import com.ukcorp.ieum.care.repository.CareRepository;
import com.ukcorp.ieum.chat.dto.ChatHistoryDto;
import com.ukcorp.ieum.chat.dto.ChatHistorySaveDto;
import com.ukcorp.ieum.chat.dto.request.ChatHIstoryDto;
import com.ukcorp.ieum.chat.dto.response.ChatHistoryResponseDto;
import com.ukcorp.ieum.chat.entity.ChatHistory;
import com.ukcorp.ieum.chat.entity.Subject;
import com.ukcorp.ieum.chat.mapper.ChatHistoryMapper;
import com.ukcorp.ieum.chat.repository.ChatHistoryRepository;
import com.ukcorp.ieum.chat.service.ChatHistoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Slf4j
public class ChatHistoryServiceImpl implements ChatHistoryService {

    private final ChatHistoryRepository chatHistoryRepository;
    private final ChatHistoryMapper chatHistoryMapper;
    private final CareRepository careRepository;

    @Override
    public Long getCareNoBySerial(String serial) {
        return careRepository.findCareInfoByCareSerial(serial)
                .orElseThrow(() -> new NoSuchElementException("NOT FOUND CARE INFO")).getCareNo();
    }

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

    /**
     * 이음이 -> 어르신 메시지 저장
     *
     * @param message
     * @param emotion
     */
    @Override
    public void saveIeumChat(String message, String emotion, Long careNo) {
        // dto 생성
        ChatHistorySaveDto newChat = ChatHistorySaveDto.builder()
                .chatDate(LocalDateTime.now())
                .speaker(Subject.AI)
                .listener(Subject.USER)
                .messageContent(message)
                .emotion(convertEmotion(emotion))
                .careInfo(getCareInfo(careNo)).build();
        log.info("생성한 새로운 채팅 DTO >> " + newChat.toString());

        // dto to entity
        ChatHistory chatHistory = chatHistoryMapper.chatHistorySaveDtoToChatHistory(newChat);
        log.info("변환한 Entity >> " + chatHistory.toString());

        // save
        chatHistoryRepository.save(chatHistory);
    }

    /**
     * 어르신 -> 이음이 메시지 저장
     *
     * @param message
     * @param emotion
     */
    @Override
    public void saveMemberChat(String message, String emotion, Long careNo) {
        ChatHistorySaveDto newChat = ChatHistorySaveDto.builder()
                .chatDate(LocalDateTime.now())
                .speaker(Subject.USER)
                .listener(Subject.AI)
                .messageContent(message)
                .emotion(convertEmotion(emotion))
                .careInfo(getCareInfo(careNo)).build();
        log.info("생성한 새로운 채팅 DTO >> " + newChat.toString());

        // dto to entity
        ChatHistory chatHistory = chatHistoryMapper.chatHistorySaveDtoToChatHistory(newChat);
        log.info("변환한 Entity >> " + chatHistory.toString());

        // save
        chatHistoryRepository.save(chatHistory);
    }

    public CareInfo getCareInfo(Long careNo) {
        return careRepository.findCareInfoByCareNo(careNo)
                .orElseThrow(() -> new UsernameNotFoundException("NOT FOUND CARE INFO"));
    }

    /**
     * 감정 상태 변환
     *
     * @param emotion
     * @return
     */
    public String convertEmotion(String emotion) {
        if (emotion.equals("negative")) {
            return "SAD";
        } else if (emotion.equals("positive")) {
            return "HAPPY";
        }
        return "COMMON";
    }

//  @Override
//  public void saveChat(ChatMessageDto chatMessageDto) {
//
//  }
}
