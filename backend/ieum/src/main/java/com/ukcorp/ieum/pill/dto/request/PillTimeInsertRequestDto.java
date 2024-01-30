package com.ukcorp.ieum.pill.dto.request;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
/**
 * @author : 김영욱
 * TimeRequest를 받기 위한 Dto
 */
public class PillTimeInsertRequestDto {
    private Long pillInfoNo;
    private LocalDateTime pillTakeTime;
}
