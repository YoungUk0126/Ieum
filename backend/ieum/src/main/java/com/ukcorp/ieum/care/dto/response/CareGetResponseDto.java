package com.ukcorp.ieum.care.dto.response;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CareGetResponseDto {
    private Long careNo;
    private String careName;
    private String careGender;
    private LocalDate careBirth;
    private String carePhone;
    private String careAddr;
    private String careImage;
    private String careSerial;
}
