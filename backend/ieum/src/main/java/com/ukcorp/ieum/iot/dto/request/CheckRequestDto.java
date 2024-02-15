package com.ukcorp.ieum.iot.dto.request;

import lombok.*;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CheckRequestDto {
  private String code;
  private String endPoint;
}
