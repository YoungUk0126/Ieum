package com.ukcorp.ieum.pill.dto.response;

import com.ukcorp.ieum.pill.entity.PillInfo;
import com.ukcorp.ieum.pill.entity.PillMethod;
import com.ukcorp.ieum.pill.entity.PillTime;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class PillInfoJoinResponseDto {
  private Long pillInfoNo;
  private String pillName;
  private Long careNo;
  private LocalDate pillStartDate;
  private LocalDate pillEndDate;
  private String pillMethod;
  private String pillDate;
  private List<PillTimeGetResponseDto> pillTimes;

  public PillInfoJoinResponseDto(PillInfo pillInfo) {
    this.pillInfoNo = pillInfo.getPillInfoNo();
    this.pillName = pillInfo.getPillName();
    this.careNo = pillInfo.getCareInfo().getCareNo();
    this.pillStartDate = pillInfo.getPillStartDate();
    this.pillEndDate = pillInfo.getPillEndDate();
    this.pillMethod = pillInfo.getPillMethod().toString();
    this.pillDate = pillInfo.getPillDate();
    this.pillTimes = new ArrayList<>();
    for(PillTime pillTime: pillInfo.getPillTimes()) {
      this.pillTimes.add(new PillTimeGetResponseDto(pillTime));
    }
  }

}
