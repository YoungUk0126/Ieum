package com.ukcorp.ieum.api.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * API 문자 요청 형식
 * Naver Cloud에 문자 전송을 요청할때 사용하는 DTO
 * @author : dachan
 * @fileName : SmsRequestDTO
 * @since : 01/20/24
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SmsRequestDTO {
  String type;
  String contentType;
  String countryCode;
  String from;
  String content;
  List<MessageDTO> messages;
}