package com.ukcorp.ieum.pill.dto;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class PillInfoDto {
    private LocalDate startDate;
    private LocalDate EndDate;
    private String pillName;
}
