package com.ukcorp.ieum.pill.dto.request;

import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class PutPillInfoRequestDto {
    private Long pillInfoNo;
    private String pillName;
    private LocalDate startDate;
    private LocalDate endDate;
    private String pillMethod;
    private List<PillTimeRequestDto> pillTimes;

}
