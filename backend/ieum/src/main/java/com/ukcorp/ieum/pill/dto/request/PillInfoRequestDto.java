package com.ukcorp.ieum.pill.dto.request;

import lombok.*;

import java.time.LocalDate;

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
public class PillInfoRequestDto {
    private String pillName;
    private LocalDate startDate;
    private LocalDate EndDate;
    private String pillMethod;

}
