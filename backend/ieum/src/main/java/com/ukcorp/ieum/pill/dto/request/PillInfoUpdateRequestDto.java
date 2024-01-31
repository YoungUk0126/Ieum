package com.ukcorp.ieum.pill.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate pillStartDate;
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate pillEndDate;
    private String pillMethod;
    private String pillDate;
    private List<PillTimeUpdateRequestDto> pillTimes;

}
