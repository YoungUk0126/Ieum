package com.ukcorp.ieum.sleep.dto.response;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class SleepResponseDto {
  private Long sleepInfoNo;
  private Long careNo;
  private LocalDateTime sleepStartTime;
  private LocalDateTime sleepEndTime;
}
