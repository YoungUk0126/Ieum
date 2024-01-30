package com.ukcorp.ieum.pill.dto.request;

import com.ukcorp.ieum.pill.entity.PillMethod;
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
    private PillMethod pillMethod;
    private String pillDate;
    private List<PillTimeInsertRequestDto> pillTimes;

}
