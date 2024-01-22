package com.ukcorp.ieum.event.entity;

import com.ukcorp.ieum.care.entity.CareInfo;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class RegularEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "EVENT_NO")
    private Long eventNo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CARE_NO")
    private CareInfo careInfo;

    @Column(name = "EVENT_NAME")
    private String eventName;

    @Column(name = "EVENT_DATE")
    private LocalDate eventDate;

}
