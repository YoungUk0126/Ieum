package com.ukcorp.ieum.care.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "CARE_INFO")
public class CareInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CARE_NO")
    private Long careNo;

    @Column(name = "CARE_NAME")
    private String careName;

    @Enumerated(EnumType.STRING)
    @Column(name = "CARE_GENDER")
    private Gender careGender;

    @Column(name = "CARE_BIRTH")
    private LocalDate careBirth;

    @Column(name = "CARE_ADDR")
    private String careAddr;

    @Column(name = "CARE_PHONE")
    private String carePhone;

    @Column(name = "CARE_IMAGE")
    private String careImage;

    @Column(name = "CARE_SERIAL")
    private String careSerial;

}

