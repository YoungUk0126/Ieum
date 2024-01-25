package com.ukcorp.ieum.sleep.dto.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class SleepInfoResponseDto {

    private Long sleepInfoNo;

    private Long careNo;

    private Long sleepStartTime;

    private Long sleepEndTime;
}

