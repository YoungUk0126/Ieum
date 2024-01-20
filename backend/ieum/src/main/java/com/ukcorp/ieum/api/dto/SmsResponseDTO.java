package com.ukcorp.ieum.api.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import lombok.NoArgsConstructor;
/**
 * API 문자 응답 형식
 * Naver Cloud에서 날라오는 응답
 * @author : dachan
 * @fileName : SmsResponseDTO
 * @since : 01/20/24
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SmsResponseDTO {
  String requestId;
  LocalDateTime requestTime;
  String statusCode;
  String statusName;
}