package com.ukcorp.ieum.care.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CareRequestDto {
    private String careName;
    private String careGender;
    private LocalDate careBirth;
    private String carePhone;
    private String careAddr;
}
