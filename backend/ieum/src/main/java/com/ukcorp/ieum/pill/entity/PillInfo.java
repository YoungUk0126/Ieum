package com.ukcorp.ieum.pill.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
@Table(name = "PILL_INFO")
public class PillInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long pillInfoNo;
    private long careNo;
    private String pillName;
    private LocalDate pillStartDate;
    private LocalDate pillEndDate;
//    식전/식후
    private String pillMethod;
    @OneToMany
    private List<PillTime> pillTimes;
}
