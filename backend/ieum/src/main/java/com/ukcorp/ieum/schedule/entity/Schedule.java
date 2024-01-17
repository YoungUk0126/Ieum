package com.ukcorp.ieum.schedule.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
@Table(name = "SCHEDULE")
public class Schedule {
    @Id
    private long scheduleNo;
    private long careNo;
    private LocalDate scheduleDate;
    private String scheduleName;
}
