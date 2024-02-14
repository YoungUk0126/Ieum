package com.ukcorp.ieum.api.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;
import lombok.Setter;

/**
 * ChatGPT 메세지 형식
 *
 * @author : dachan
 * @fileName : ChatGPTRequestDto
 * @since : 01/19/24
 */
@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class ChatGPTRequestDto {
    // 프롬프트에 사용할 GPT 모델 명
    private String model;

    /* 프롬프트 Message
    * "messages": [
    *      {"role": "system", "content": "당신은 도움이 되는 어시스턴트입니다."},
    *      {"role": "user", "content": "오늘 날씨 어때요?"},
    *      {"role": "assistant", "content": "오늘 날씨는 맑고 기온은 25도입니다."}
    *    ]
    * */
    private List<Map<String, Object>> messages;
    /*
    * 대화의 온도로써 0~무한대까지 가능
    * 0에 가까울수록 결정적이고 일관성, 멀어질수록 다양하고 창의적
    */
    private float temperature;

}