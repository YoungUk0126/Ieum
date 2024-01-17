package com.ukcorp.ieum.pill.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
@Table(name = "PILL_TIME")
public class PillTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long pillTimeNo;
    private long pillInfoNo;
//    복용 요일
    private String pillDate;
    private LocalDateTime pillTime;
}
