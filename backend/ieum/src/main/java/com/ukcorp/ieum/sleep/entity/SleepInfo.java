package com.ukcorp.ieum.sleep.entity;

import com.ukcorp.ieum.care.entity.CareInfo;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Time;

@Entity
@Getter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "SLEEP_INFO")
public class SleepInfo {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "SLEEP_INFO_NO")
  private Long sleepInfoNo;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "CARE_NO")
  private CareInfo careInfo;

  @Column(name = "SLEEP_START_TIME")
  private Time sleepStartTime;

  @Column(name = "SLEEP_END_TIME")
  private Time sleepEndTime;
}

