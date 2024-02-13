package com.ukcorp.ieum.sleep.dto.response;

import lombok.*;

import java.sql.Time;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class SleepResponseDto {
  private Long sleepInfoNo;
  private Long careNo;
  private Long sleepStartTime;
  private Long sleepEndTime;
}
