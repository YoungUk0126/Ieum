package com.ukcorp.ieum.care.dto.request;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CareUpdateRequestDto {
    private LocalDate careBirth;
    private String careAddr;
    private String careName;
    private String carePhone;
    private String careGender;
    private String careImage;
    private String careSerial;
}
