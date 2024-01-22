package com.ukcorp.ieum.api.controller;


import com.ukcorp.ieum.api.service.GoogleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
/**
 * Google API 컨트롤러
 *
 * @author : dachan
 * @fileName : GoogleController
 * @since : 01/22/24
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/google")
public class GoogleController {

  private final GoogleService googleService;

  /**
   * 구글 TTS 사용 method
   * @param text
   * @return mpe file
   */
  @GetMapping("/tts")
  public ResponseEntity<byte[]> getTextToSpeech(@RequestParam String text) {
    try {
      byte[] audioData = googleService.convertTextToSpeech(text);
      HttpHeaders headers = new HttpHeaders();
      headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
      headers.setContentDispositionFormData("attachment", "output.mp3");

      // Http response로 mp3 파일 전송
      return new ResponseEntity<>(audioData, headers, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
}
