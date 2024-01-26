package com.ukcorp.ieum.sleep.dto.request;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class SleepUpdateRequestDto {
  private Long sleepInfoNo;
  private Long careNo;
  private LocalDateTime sleepStartTime;
  private LocalDateTime sleepEndTime;
}
