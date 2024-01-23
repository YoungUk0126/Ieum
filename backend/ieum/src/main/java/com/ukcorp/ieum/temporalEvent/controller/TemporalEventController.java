package com.ukcorp.ieum.temporalEvent.controller;

import com.ukcorp.ieum.temporalEvent.dto.TemporalEventDto;
import com.ukcorp.ieum.temporalEvent.service.TemporalEventService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/event")
@RequiredArgsConstructor
public class TemporalEventController {

  private final TemporalEventService temporalEventService;

  /**
   * 일정 관련 리스트 조회
   * @param careNo
   * @return List<TemporalEventDto>
   */
  @GetMapping("/")
  public ResponseEntity<Map<String, Object>> getEvent(@RequestParam Long careNo) {
    try {
      List<TemporalEventDto> list = temporalEventService.getList(careNo);
      return handleSuccess(list);
    } catch (Exception exception) {
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
