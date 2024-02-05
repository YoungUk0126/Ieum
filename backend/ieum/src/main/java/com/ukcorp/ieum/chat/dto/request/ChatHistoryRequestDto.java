package com.ukcorp.ieum.chat.dto.request;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChatHistoryRequestDto {

  private LocalDate startDate;
  private LocalDate endDate;

  public ChatHIstoryDto toDto() {
    return ChatHIstoryDto.builder()
            .startDate(this.getStartDate().atStartOfDay())
            .endDate(this.getEndDate().atTime(LocalTime.MAX))
            .build();
  }
}
