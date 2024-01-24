package com.ukcorp.ieum.api.controller;

import com.ukcorp.ieum.api.service.ChatGPTService;
import com.ukcorp.ieum.api.service.GoogleService;
import com.ukcorp.ieum.api.service.NaverService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/ieum")
public class IeumAPIController {
  private final GoogleService googleService;
  private final NaverService naverService;
  private final ChatGPTService chatGPTService;

  @PostMapping("")
  public ResponseEntity<byte[]> getSTT(@RequestParam("file") MultipartFile file) {
    try {
      String stt = naverService.getSTT(file);
      if (stt.equals("Fail")) {
        throw new Exception("STT 실패");
      }

      String chat = chatGPTService.prompt(stt);

      byte[] audioData = googleService.convertTextToSpeech(chat);
      HttpHeaders headers = new HttpHeaders();
      headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
      headers.setContentDispositionFormData("attachment", "output.mp3");

      // Http response로 mp3 파일 전송
      return new ResponseEntity<>(audioData, headers, HttpStatus.OK);

    } catch (Exception e) {
      System.out.println(e.getMessage());
      HttpHeaders headers = new HttpHeaders();
      headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
      headers.setContentDispositionFormData("attachment", "output.mp3");

      return new ResponseEntity<>(null, headers, HttpStatus.OK);
    }
  }


  private ResponseEntity<Map<String,Object>> handleSuccess(Object data){
    Map<String, Object> result = new HashMap<>();
    result.put("success", true);
    result.put("data", data);
    return new ResponseEntity<Map<String,Object>>(result, HttpStatus.OK);
  }

  private ResponseEntity<Map<String,Object>> handleFail(Object data){
    Map<String, Object> result = new HashMap<>();
    result.put("success", false);
    result.put("data", data);
    return new ResponseEntity<Map<String,Object>>(result, HttpStatus.OK);
  }

}
