package com.ukcorp.ieum.pill.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
@Table(name = "PILL_TIME")
public class PillTime {
    @Id
    @Column(name = "PILL_TIME_NO")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pillTimeNo;

    @ManyToOne
    @JoinColumn(name = "PILL_INFO_NO")
    private PillInfo pillInfo;

    @Column(name = "PILL_TAKE_TIME")
    private Long pillTakeTime;
}
