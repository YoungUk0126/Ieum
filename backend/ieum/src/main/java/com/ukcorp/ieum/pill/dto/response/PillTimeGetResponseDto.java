package com.ukcorp.ieum.pill.dto.response;

import com.ukcorp.ieum.pill.entity.PillTime;
import lombok.*;

/**
 * @author : 김영욱
 * PillTime를 보내주기위한 Dto
 * 필드는 같지만 명시적으로 나눠줬음
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class PillTimeGetResponseDto {
    private Long pillTimeNo;
    private Long pillInfoNo;
    private Long pillTime;


//    Fetch Join을 위한 생성자
    public PillTimeGetResponseDto(PillTime pt ){
        this.pillTime = pt.getPillTakeTime();
    }
}
