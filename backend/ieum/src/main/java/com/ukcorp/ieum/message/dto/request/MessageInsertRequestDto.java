package com.ukcorp.ieum.message.dto.request;


import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class MessageInsertRequestDto {

  private Long careNo;

  private String messageName;

  private String messageType;

  private LocalDate sendDate;
}
