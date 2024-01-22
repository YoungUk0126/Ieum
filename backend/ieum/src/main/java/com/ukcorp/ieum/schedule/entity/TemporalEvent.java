package com.ukcorp.ieum.schedule.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "TEMPORAL_EVENT")
public class TemporalEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "EVENT_NO")
    private Long eventNo;
    private Long careNo;
    private LocalDate scheduleDate;
    private String scheduleName;
}
