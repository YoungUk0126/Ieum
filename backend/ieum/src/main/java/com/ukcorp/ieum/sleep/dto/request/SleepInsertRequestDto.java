package com.ukcorp.ieum.sleep.dto.request;

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
public class SleepInsertRequestDto {
  private Long sleepStartTime;
  private Long sleepEndTime;
}
