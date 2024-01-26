package com.ukcorp.ieum.message.dto.request;


import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class MessageInsertRequestDto {

  private Long careNo;

  private String messagePeople;

  private String messageName;

  private String messageType;

  private LocalDate messageTime;

  private MultipartFile file;
}
