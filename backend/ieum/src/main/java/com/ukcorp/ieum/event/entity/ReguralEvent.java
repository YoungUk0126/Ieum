package com.ukcorp.ieum.event.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class ReguralEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long eventNo;
    private Long careNo;
    private String eventName;
    private LocalDate eventDate;

}
