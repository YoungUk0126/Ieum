package com.ukcorp.ieum.event.controller;


import com.ukcorp.ieum.event.dto.request.EventInsertRequestDto;
import com.ukcorp.ieum.event.dto.request.EventUpdateRequestDto;
import com.ukcorp.ieum.event.dto.response.EventGetResponseDto;
import com.ukcorp.ieum.event.service.EventServiceImpl;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/api/ani")
@Slf4j
public class EventController {

  private final EventServiceImpl eventService;

  /**
   * 피보호자의 FK로 피보호자 기념일 정보 리스트를 반환하는 기능
   *
   * @param careNo
   */
  @GetMapping("/list/{care-no}")
  public ResponseEntity getEventList(@PathVariable("care-no") Long careNo) {
    // TODO : 기념일 정보 전체 조회 로직 추가
    try {
      List<EventGetResponseDto> eventGetResponseDtoList = eventService.getAllEvent(careNo);
      return handleSuccess(eventGetResponseDtoList);
    } catch (Exception e) {
      log.debug(e.getMessage());
      return handleError("Fail");
    }
  }


  /**
   * 기념일의 pk로 피보호자 기념일 정보를 반환하는 기능
   *
   * @param eventNo
   */
  // TODO : 기념일 정보 상세 조회
  @GetMapping("/detail/{event-no}")
  public ResponseEntity<Map<String, Object>> getEvent(@PathVariable("event-no") Long eventNo) {

    try {
      EventGetResponseDto eventGetResponseDto = eventService.getEvent(eventNo);
      return handleSuccess(eventGetResponseDto);
    } catch (Exception e) {
      log.debug(e.getMessage());
      return handleError("Fail");
    }
  }

  /**
   * 일정 등록
   *
   * @param event
   * @return
   */
  @PostMapping
  public ResponseEntity<Map<String, Object>> postsEvent(@RequestBody EventInsertRequestDto event) {
    try {
      eventService.insertEvent(event);
      return handleSuccess(event);
    } catch (Exception e) {
      log.debug(e.getMessage());
      return handleError("Fail");
    }
  }

  @PutMapping
  public ResponseEntity<Map<String, Object>> putsEvent(@RequestBody EventUpdateRequestDto event) {
    try {
      eventService.updateEvent(event);
      return handleSuccess("");
    } catch (Exception e) {
      log.debug(e.getMessage());
      return handleError("Fail");
    }
  }

  @DeleteMapping("/{event-no}")
  public ResponseEntity<Map<String, Object>> deleteEvent(@PathVariable("event-no") Long eventNo) {
    try {
      eventService.deleteEvent(eventNo);
      return handleSuccess("");
    } catch (Exception e) {
      log.debug(e.getMessage());
      return handleError("Fail");
    }
  }


  private ResponseEntity<Map<String, Object>> handleSuccess(Object data) {
    Map<String, Object> result = new HashMap<>();

    result.put("success", true);
    result.put("data", data);
    return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
  }

  private ResponseEntity<Map<String, Object>> handleError(Object data) {
    Map<String, Object> result = new HashMap<>();
    result.put("success", false);
    result.put("data", data);
    return new ResponseEntity<Map<String, Object>>(result, HttpStatus.BAD_REQUEST);
  }
}
