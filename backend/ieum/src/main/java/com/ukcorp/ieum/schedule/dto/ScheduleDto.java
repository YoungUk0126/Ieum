package com.ukcorp.ieum.schedule.dto;


import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class ScheduleDto {

    private String schName;
    private LocalDate schDate;
    private LocalDateTime schTime;
}
