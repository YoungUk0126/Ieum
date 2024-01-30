package com.ukcorp.ieum.pill.dto.response;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class TotalPillGetResponseDto {
  private PillInfoGetResponseDto pillInfo;
  private List<PillTimeGetResponseDto> pillTimes;
}
