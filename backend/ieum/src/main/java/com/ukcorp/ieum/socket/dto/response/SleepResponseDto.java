package com.ukcorp.ieum.socket.dto.response;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SleepResponseDto implements Response{

    Long sleepStartTime;
    Long sleepEndTime;
}
