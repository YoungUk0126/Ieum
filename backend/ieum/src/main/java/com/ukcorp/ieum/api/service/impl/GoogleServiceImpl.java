package com.ukcorp.ieum.api.service.impl;

import com.google.api.gax.core.FixedCredentialsProvider;
import com.google.auth.oauth2.ServiceAccountCredentials;
import com.google.cloud.texttospeech.v1.*;
import com.ukcorp.ieum.api.service.GoogleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;

/**
 * Google Service 구현체
 *
 * @author : dachan
 * @fileName : GoogleServiceImpl
 * @since : 01/22/24
 */
@Service
@Slf4j
public class GoogleServiceImpl implements GoogleService {

  /**
   * 입력된 text를 사용해 mp3 byte[] 로 반환
   * @param text
   * @return mp3 byte[]
   * @throws Exception
   */
  public byte[] convertTextToSpeech(String text) throws Exception {
    TextToSpeechSettings settings;
    try{
      log.debug("[+] 사용자 인증 정보 로드 시작");
      // TTS 사용자 인증 키 Json 파일 저장
      settings =
              TextToSpeechSettings.newBuilder()
                      .setCredentialsProvider(FixedCredentialsProvider.create(ServiceAccountCredentials.fromStream(new FileInputStream("active-incline-388904-6290fed3bb30.json"))))
                      .build();
      log.debug("[+] 사용자 인증 성공");
    }catch (Exception e){
      throw new Exception("인증 정보 오류");
    }

    try (TextToSpeechClient client = TextToSpeechClient.create(settings)) {
      log.debug("[+] 텍스트 음성 변환 시작");
      // 입력으로 들어온 text를 활용해 SynthesisInput 생성ㄴ
      SynthesisInput input = SynthesisInput.newBuilder().setText(text).build();

      // 음성 설정: 한국어, 여성
      VoiceSelectionParams voice =
              VoiceSelectionParams.newBuilder()
                      .setLanguageCode("ko-KR")
                      .setName("ko-KR-Standard-D")
                      .setSsmlGender(SsmlVoiceGender.FEMALE)
                      .build();
      // 오디오 설정으로 인코딩은 기본 LINEAR16
      AudioConfig audioConfig =
              AudioConfig.newBuilder().setAudioEncoding(AudioEncoding.LINEAR16).build();

      // client측 완성된 정보를 서버로 전송해서 response 받기
      // response는 byte[] 형태로 날라온다.
      SynthesizeSpeechResponse response =
              client.synthesizeSpeech(input, voice, audioConfig);
      log.debug("[+] 텍스트 음성 변환 성공");
      return response.getAudioContent().toByteArray();
    }catch (Exception e){
      log.debug("[-] 텍스트 음성 변환 오류");
      throw new Exception("변환 과정 오류");
    }
  }
}
