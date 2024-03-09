package com.ukcorp.ieum.temporalEvent.entity;

import com.ukcorp.ieum.care.entity.CareInfo;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

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

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "CARE_NO")
  private CareInfo careInfo;

  @Column(name = "EVENT_NAME")
  private String eventName;

  @Column(name = "EVENT_DATE")
  private LocalDateTime eventDate;
}
