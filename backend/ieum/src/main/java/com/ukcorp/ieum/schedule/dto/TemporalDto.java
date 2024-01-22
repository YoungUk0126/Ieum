package com.ukcorp.ieum.schedule.dto;


import com.ukcorp.ieum.care.entity.CareInfo;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class TemporalDto {

  private Long eventNo;

  private CareInfo careInfo;

  private String eventName;

  private LocalDate eventDate;
}
