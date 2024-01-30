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
public class PillInfoUpdateRequestDto {
    private Long pillInfoNo;
    private String pillName;
    private LocalDate startDate;
    private LocalDate endDate;
    private String pillMethod;
    private String pillDate;
    private List<PillTimeRequestDto> pillTimes;

}
