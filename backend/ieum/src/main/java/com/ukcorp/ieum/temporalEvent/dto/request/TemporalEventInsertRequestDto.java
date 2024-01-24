package com.ukcorp.ieum.temporalEvent.dto.request;


import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class TemporalEventInsertRequestDto {

  private Long careNo;

  private String eventName;

  private LocalDate eventDate;
}
