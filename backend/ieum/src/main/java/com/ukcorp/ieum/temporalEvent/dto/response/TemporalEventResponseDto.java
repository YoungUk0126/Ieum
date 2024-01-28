package com.ukcorp.ieum.temporalEvent.dto.response;


import lombok.*;

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

  private LocalDate eventDate;
}
