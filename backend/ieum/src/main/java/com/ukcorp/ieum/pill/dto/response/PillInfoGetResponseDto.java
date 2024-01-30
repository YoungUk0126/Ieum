package com.ukcorp.ieum.pill.dto.response;

import lombok.*;

import java.time.LocalDate;
import java.util.List;

/**
 * @author : 김영욱
 * PillInfo를 보내주기위한 Dto
 * 필드는 같지만 명시적으로 나눠줬음
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class PillInfoGetResponseDto {
    private Long pillInfoNo;
    private String pillName;
    private Long careNo;
    private LocalDate startDate;
    private LocalDate endDate;
    private String pillMethod;
    private String pillDate;
}
