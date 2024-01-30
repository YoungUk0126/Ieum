package com.ukcorp.ieum.pill.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
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
/**
 * @author : 김영욱
 * InfoRequest를 받기 위한 Dto
 */
public class PillInfoInsertRequestDto {
    private Long careNo;
    private String pillName;
//    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate pillStartDate;
//    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate pillEndDate;
    private String pillMethod;
    private String pillDate;
    private List<PillTimeInsertRequestDto> pillTimes;
}
