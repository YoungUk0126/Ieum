package com.ukcorp.ieum.pill.dto.request;

import com.ukcorp.ieum.pill.entity.PillMethod;
import com.ukcorp.ieum.pill.entity.PillTime;
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
    private Long careNo;
    private String pillName;
    private LocalDate startDate;
    private LocalDate endDate;
    private String pillMethod;
    private String pillDate;
    private List<PillTimeUpdateRequestDto> pillTimes;

}
