package com.ukcorp.ieum.message.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/message")
@RequiredArgsConstructor
public class MessageController {

  private final MessageService messageService;

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

  @DeleteMapping("/{messageNo}")
  public ResponseEntity<Map<String, Object>> deleteMessage(@PathVariable("messageNo") Long messageNo) {
    try{
      messageService.deleteMessage(messageNo);
      return handleSuccess("");
    }catch (Exception e){
      log.debug(e.getMessage());
      return handleFail("Fail");
    }
  }

  @PostMapping
  public ResponseEntity<Map<String, Object>> postsMessage(@RequestBody MessageRequestDto message) {
    try{
      messageService.registMessage(message);
      return handleSuccess("");
    }catch (Exception e){
      log.debug(e.getMessage());
      return handleFail("Fail");
    }
  }

  @PutMapping
  public ResponseEntity<Map<String, Object>> putMessage(@RequestBody MessageRequestDto message) {
    try{
      messageService.modifyMessage(message);
      return handleSuccess("");
    }catch (Exception e){
      log.debug(e.getMessage());
      return handleFail("Fail");
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
    return new ResponseEntity<Map<String, Object>>(result, HttpStatus.BAD_REQUEST);
  }

  private ResponseEntity<Map<String, Object>> handleError(Object data) {
    Map<String, Object> result = new HashMap<>();
    result.put("success", false);
    result.put("data", data);
    return new ResponseEntity<Map<String, Object>>(result, HttpStatus.BAD_REQUEST);
  }
}
