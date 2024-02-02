package com.ukcorp.ieum.chat.dto;

import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChatHistoryDto {

    private Long id;
    private String speaker;
    private String listener;
    private String message;
    private String emotion;
    private LocalDateTime date;
}
