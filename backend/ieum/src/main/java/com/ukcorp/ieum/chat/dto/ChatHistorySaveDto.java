package com.ukcorp.ieum.chat.dto;

import com.ukcorp.ieum.care.entity.CareInfo;
import com.ukcorp.ieum.chat.entity.Subject;
import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@ToString
public class ChatHistorySaveDto {
    private CareInfo careInfo;

    private String messageContent;

    private Subject speaker;

    private Subject listener;

    private String emotion;

    private LocalDateTime chatDate;
}
