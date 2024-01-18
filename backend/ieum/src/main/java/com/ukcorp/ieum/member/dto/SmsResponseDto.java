package com.ukcorp.ieum.member.dto;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SmsResponseDto {
    String requestId;
    LocalDateTime requestTime;
    String statusCode;
    String statusName;
}
