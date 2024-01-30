package com.ukcorp.ieum.pill.dto.request;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class PillTimeUpdateRequestDto {
  private Long pillTimeNo;
  private Long pillInfoNo;
  private Long pillTakeTime;
}
