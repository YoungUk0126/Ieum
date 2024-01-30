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
/**
 * @author : 김영욱
 * InfoRequest를 받기 위한 Dto
 */
public class PillInfoInsertRequestDto {
    private Long careNo;
    private String pillName;
    private LocalDate startDate;
    private LocalDate endDate;
    private String pillMethod;
    private String pillDate;
    private List<PillTimeRequestDto> pillTimes;

}
