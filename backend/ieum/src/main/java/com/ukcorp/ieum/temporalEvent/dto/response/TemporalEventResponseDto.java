package com.ukcorp.ieum.temporalEvent.dto.response;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class TemporalEventResponseDto {

  private Long eventNo;

  private Long careNo;

  private String eventName;

  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private LocalDate eventDate;
}
