package com.ukcorp.ieum.temporalEvent.dto;


import com.ukcorp.ieum.care.entity.CareInfo;
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

  private CareInfo careInfo;

  private String eventName;

  private LocalDate eventDate;
}
