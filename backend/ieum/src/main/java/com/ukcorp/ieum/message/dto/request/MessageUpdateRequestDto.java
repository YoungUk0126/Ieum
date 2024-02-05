package com.ukcorp.ieum.message.dto.request;


import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class MessageUpdateRequestDto {
  private Long messageNo;

  private String messageSender;

  private String messageName;

  private String messageType;

  private LocalDate messageTime;
}
