package com.ukcorp.ieum.care.entity;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
@Entity
public class CareInfo {
    
    private int careId;
    private int memberPk;
    private String careName;
    private String careGender;
    private LocalDate careBirth;
    private String careAddr;
    private String carePhone;
    private String careProfile;
}
