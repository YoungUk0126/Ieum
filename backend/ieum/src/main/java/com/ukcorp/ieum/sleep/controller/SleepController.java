package com.ukcorp.ieum.sleep.controller;

import com.ukcorp.ieum.sleep.dto.request.SleepInsertRequestDto;
import com.ukcorp.ieum.sleep.dto.request.SleepUpdateRequestDto;
import com.ukcorp.ieum.sleep.dto.response.SleepResponseDto;
import com.ukcorp.ieum.sleep.service.SleepService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/sleepMode")
@RequiredArgsConstructor
public class SleepController {

  private final SleepService sleepService;


  /**
   * 취침 시간 관련 상세 정보(개별) 조회
   *
   * @param
   * @return SleepResponseDto
   */
  @GetMapping
  public ResponseEntity<Map<String, Object>> getSleepDetail() {
    try {
      SleepResponseDto sleep = sleepService.getSleep();
      return handleSuccess(sleep);
    } catch (Exception exception) {
      log.debug(exception.getMessage());
      return handleFail("Fail");
    }
  }

  /**
   * 취침 시간 삭제
   *
   * @param sleepInfoNo
   */
  @DeleteMapping("/{sleep-info-no}")
  public ResponseEntity<Map<String, Object>> deleteSleep(@PathVariable("sleep-info-no") Long sleepInfoNo) {
    try {
      sleepService.deleteSleep(sleepInfoNo);
      return handleSuccess("");
    } catch (Exception e) {
      log.debug(e.getMessage());
      return handleFail("Fail");
    }
  }

  /**
   * 취침 시간 등록
   *
   * @param sleep
   * @return
   */
  @PostMapping
  public ResponseEntity<Map<String, Object>> insertSleep(@RequestBody SleepInsertRequestDto sleep) {
    try {
      sleepService.registSleep(sleep);
      return handleSuccess("");
    } catch (Exception e) {
      log.debug(e.getMessage());
      return handleFail("Fail");
    }
  }

  /**
   * 취침 시간 수정
   *
   * @param sleep
   * @return
   */
  @PutMapping
  public ResponseEntity<Map<String, Object>> modifySleep(@RequestBody SleepUpdateRequestDto sleep) {
    try {
      sleepService.modifySleep(sleep);
      return handleSuccess("");
    } catch (Exception e) {
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
    return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
  }
}
