package com.ukcorp.ieum.care.dto.request;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class CareInsertRequestDto {
    private LocalDate careBirth;
    private String careAddr;
    private String careName;
    private String carePhone;
    private String careGender;
}
