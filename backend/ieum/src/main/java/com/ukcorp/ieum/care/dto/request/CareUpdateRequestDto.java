package com.ukcorp.ieum.care.dto.request;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CareUpdateRequestDto {
    private Long careNo;
    private String careName;
    private String careGender;
    private LocalDate careBirth;
    private String carePhone;
    private String careAddr;
}
