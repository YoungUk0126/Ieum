package com.ukcorp.ieum.chat.dto.request;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChatHIstoryRequestDto {

  private LocalDateTime startDate;
  private LocalDateTime endDate;
}



