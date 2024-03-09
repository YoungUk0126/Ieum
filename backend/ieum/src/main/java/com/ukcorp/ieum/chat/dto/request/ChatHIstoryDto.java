package com.ukcorp.ieum.chat.dto.request;

import lombok.*;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChatHIstoryDto {

  private LocalDateTime startDate;
  private LocalDateTime endDate;

}



