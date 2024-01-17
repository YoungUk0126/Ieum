package com.ukcorp.ieum.pill.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class PillTimeDto {
    private String pillDay;
    private LocalDateTime pillTime;
}
