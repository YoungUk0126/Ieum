package com.ukcorp.ieum.chat.service;

import com.ukcorp.ieum.chat.dto.request.ChatHIstoryDto;
import com.ukcorp.ieum.chat.dto.response.ChatHistoryResponseDto;
import org.springframework.data.domain.Pageable;

public interface ChatHistoryService {

    Long getCareNoBySerial(String serial);

    ChatHistoryResponseDto getChatHistory(Long careNo, Pageable pageable, ChatHIstoryDto chatHIstoryDto);

    ChatHistoryResponseDto getChatHistoryLimit(Long careNo);

    void saveIeumChat(String message, String emotion, Long careNo);

    void saveMemberChat(String message, String emotion, Long careNo);
}
