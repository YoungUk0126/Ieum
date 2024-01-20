package com.ukcorp.ieum.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * SMS 메세지 형식
 * API간 통신이 아닌 기본 DTO 형식
 * @author : dachan
 * @fileName : MessageDTO
 * @since : 01/19/24
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MessageDTO {
  // to: 받는 번호, content: 문자 메세지 내용
  String to;
  String content;
}