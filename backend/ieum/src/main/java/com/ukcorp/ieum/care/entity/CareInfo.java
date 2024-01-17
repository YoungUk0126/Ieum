package com.ukcorp.ieum.care.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
@Entity
public class CareInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int careId;
    private int memberPk;
    private String careName;
    private String careGender;
    private LocalDate careBirth;
    private String careAddr;
    private String carePhone;
    private String careProfile;
}
