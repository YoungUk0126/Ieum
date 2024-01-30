package com.ukcorp.ieum.chat.dto.request;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChatHIstoryRequestDto {

  private LocalDate startDate;
  private LocalDate endDate;
}



