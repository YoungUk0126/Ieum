package com.ukcorp.ieum.api.controller;

import com.ukcorp.ieum.api.dto.MessageDTO;
import com.ukcorp.ieum.api.dto.SmsResponseDTO;
import com.ukcorp.ieum.api.service.NaverService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

/**
 * Naver API 컨트롤러 생성
 *
 * @author : dachan
 * @fileName : NaverController
 * @since : 01/20/24
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/naver")
public class NaverController {

  private final NaverService naverService;

  /**
   * [Method] Naver 클라우드 관련 API
   *
   * @param : String content, String Phone
   * @return ResponseEntity<Map<String, Object>>
   */
  @PostMapping("/msg")
  public ResponseEntity<Map<String, Object>> sendMsg(@RequestBody String phone,
      @RequestBody String content) {
    MessageDTO messageDto = new MessageDTO(phone, content);

    try {
      SmsResponseDTO result = naverService.sendSms(messageDto);
      if ("202".equals(result.getStatusCode())) {
        return handleSuccess("메세지 전송에 성공했습니다.");
      }else{
        return handleFail("메세지 전송 실패 ");
      }
    } catch (Exception e) {
      return handleFail("메세지 전송 실패 ");
    }
  }

  /**
   * [Method] Naver 클로바 STT
   *
   * @param : MultipartFile (mp3, aac, ac3, ogg, flac, wav) - 최대 60초
   * @return ResponseEntity<Map<String, Object>>
   */
  @PostMapping("/stt")
  public ResponseEntity<Map<String, Object>> getSTT(@RequestParam("file") MultipartFile file) {
    try {
      String result = naverService.getSTT(file);
      if (!result.equals("Fail")) {
        return handleSuccess(result);
      }else{
        return handleFail("STT 실패");
      }
    } catch (Exception e) {
      return handleFail("STT 실패 ");
    }
  }

  private ResponseEntity<Map<String, Object>> handleSuccess(Object data) {
    Map<String, Object> result = new HashMap<>();
    result.put("success", true);
    result.put("data", data);
    return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
  }

  private ResponseEntity<Map<String, Object>> handleFail(Object data) {
    Map<String, Object> result = new HashMap<>();
    result.put("success", false);
    result.put("data", data);
    return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
  }
}