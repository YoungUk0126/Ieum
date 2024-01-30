package com.ukcorp.ieum.chat.dto.response;

import com.ukcorp.ieum.chat.dto.ChatHistoryDto;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChatHistoryResponseDto {

    private List<ChatHistoryDto> contents;
    private int totalPages;
    private int totalElements;
}
