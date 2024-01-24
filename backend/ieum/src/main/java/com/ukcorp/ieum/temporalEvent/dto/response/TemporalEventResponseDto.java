package com.ukcorp.ieum.temporalEvent.dto.response;


import com.ukcorp.ieum.care.entity.CareInfo;
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

  private CareInfo careInfo;

  private String eventName;

  private LocalDate eventDate;
}
