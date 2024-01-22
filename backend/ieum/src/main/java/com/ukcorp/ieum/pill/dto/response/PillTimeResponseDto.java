package com.ukcorp.ieum.pill.dto.response;

import com.ukcorp.ieum.pill.entity.PillTime;
import lombok.*;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import java.time.LocalDateTime;

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
public class PillTimeResponseDto {
    private String pillDate;
    private Long pillTime;


//    Fetch Join을 위한 생성자
    public PillTimeResponseDto(PillTime pt ){
        this.pillDate = pt.getPillDate().toString();
        this.pillTime = pt.getPillTakeTime();
    }
}
