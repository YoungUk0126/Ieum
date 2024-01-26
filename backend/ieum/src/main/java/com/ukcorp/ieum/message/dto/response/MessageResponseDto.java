package com.ukcorp.ieum.message.dto.response;


import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class MessageResponseDto {

  private Long messageNo;

  private Long careNo;

  private String messagePeople;

  private String messageName;

  private String messageType;

  private LocalDate messageTime;
}
