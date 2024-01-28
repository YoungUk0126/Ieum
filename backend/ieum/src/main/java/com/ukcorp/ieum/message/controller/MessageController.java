package com.ukcorp.ieum.message.controller;

import com.ukcorp.ieum.message.dto.request.MessageInsertRequestDto;
import com.ukcorp.ieum.message.dto.request.MessageUpdateRequestDto;
import com.ukcorp.ieum.message.dto.response.MessageResponseDto;
import com.ukcorp.ieum.message.service.MessageService;
import java.time.LocalDate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/message")
@RequiredArgsConstructor
public class MessageController {

  private final MessageService messageService;

  /**
   * 해당 사용자에 해당하는 음성/영상 리스트 조회
   *
   * @param careNo
   * @return List<MessageResponseDto>
   */
  @GetMapping("/{careNo}")
  public ResponseEntity<Map<String, Object>> getMessage(@PathVariable("careNo") Long careNo) {
    try {
      List<MessageResponseDto> list = messageService.getList(careNo);
      return handleSuccess(list);
    } catch (Exception exception) {
      log.debug(exception.getMessage());
      return handleFail("Fail");
    }
  }

  /**
   * 메세지 번호로 해당 메세지 정보 상세 조회
   *
   * @param messageNo
   * @return MessageResponseDto
   */
  @GetMapping("/detail/{messageNo}")
  public ResponseEntity<Map<String, Object>> getMessageDetail(@PathVariable("messageNo") Long messageNo) {
    try {
      MessageResponseDto message = messageService.getDetail(messageNo);
      return handleSuccess(message);
    } catch (Exception exception) {
      log.debug(exception.getMessage());
      return handleFail("Fail");
    }
  }

  /**
   * 해당 번호에 해당하는 메세지 삭제
   *
   * @param messageNo
   * @return
   */

  @DeleteMapping("/{messageNo}")
  public ResponseEntity<Map<String, Object>> deleteMessage(@PathVariable("messageNo") Long messageNo) {
    try {
      messageService.deleteMessage(messageNo);
      return handleSuccess("");
    } catch (Exception e) {
      log.debug(e.getMessage());
      return handleFail("Fail");
    }
  }

  /**
   * 메세지 전송 등록 (파일 등록 추후 추가)
   *
   * @param
   * @return
   */
  @PostMapping
  public ResponseEntity<Map<String, Object>> postsMessage(
      @RequestParam Long careNo,
      @RequestParam String messageSender,
      @RequestParam String messageName,
      @RequestParam String messageType,
      @RequestParam LocalDate messageTime,
      @RequestParam MultipartFile file
      ) {
    try {
      log.debug("파일 저장 시작");
      uploadFile(messageName, file, messageType);
      log.debug("파일 저장 성공");
      // 메세지에 대한 정보 저장
      messageService.registMessage(new MessageInsertRequestDto(
          careNo,
          messageSender,
          messageName,
          messageType,
          messageTime
      ));

      return handleSuccess("");
    } catch (Exception e) {
      log.debug(e.getMessage());
      return handleFail("Fail");
    }
  }

  /**
   * 메세지 정보 수정
   * form-data를 받아올때는 Param을 사용해야 한다.
   *
   * @param
   * @return
   */
  @PutMapping
  public ResponseEntity<Map<String, Object>> putMessage(
      @RequestParam Long careNo,
      @RequestParam Long messageNo,
      @RequestParam String messageSender,
      @RequestParam String messageName,
      @RequestParam String messageType,
      @RequestParam LocalDate messageTime,
      @RequestParam MultipartFile file) {
    try {
      uploadFile(messageName, file, messageType);

      messageService.modifyMessage(new MessageUpdateRequestDto(
          messageNo,
          careNo,
          messageSender,
          messageName,
          messageType,
          messageTime
      ));
      return handleSuccess("");
    } catch (Exception e) {
      log.debug(e.getMessage());
      return handleFail("Fail");
    }
  }


  public static void uploadFile(String name, MultipartFile file, String type) throws IOException {
    // 파일 저장 디렉토리 경로
    String uploadDir = "./messageFile/";
    // 업로드 날라온 파일 저장
    String fileName = name;
    if(type.equals("video")){
      fileName += ".mp4";
    }else{
      fileName += ".ogg";
    }
    Path filePath = Paths.get(uploadDir, fileName);
    file.transferTo(filePath); // 파일 다운로드
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
    return new ResponseEntity<Map<String, Object>>(result, HttpStatus.BAD_REQUEST);
  }

  private ResponseEntity<Map<String, Object>> handleError(Object data) {
    Map<String, Object> result = new HashMap<>();
    result.put("success", false);
    result.put("data", data);
    return new ResponseEntity<Map<String, Object>>(result, HttpStatus.BAD_REQUEST);
  }
}
