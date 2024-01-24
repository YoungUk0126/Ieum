package com.ukcorp.ieum.temporalEvent.dto;


import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class TemporalEventDto {

  private Long eventNo;

  private Long careNo;

  private String eventName;

  private LocalDate eventDate;
}
