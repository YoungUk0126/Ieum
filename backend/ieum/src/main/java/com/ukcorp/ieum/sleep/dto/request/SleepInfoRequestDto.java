package com.ukcorp.ieum.sleep.dto.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class SleepInfoRequestDto {

    private Long sleepInfoNo;

    private Long careNo;

    private Long sleepStartTime;

    private Long sleepEndTime;
}

