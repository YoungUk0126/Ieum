package com.ukcorp.ieum.event.controller;


import com.ukcorp.ieum.event.dto.request.EventInsertRequestDto;
import com.ukcorp.ieum.event.dto.request.EventUpdateRequestDto;
import com.ukcorp.ieum.event.dto.response.EventGetResponseDto;
import com.ukcorp.ieum.event.service.EventServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/ani")
@Slf4j
public class EventController {

  private final EventServiceImpl eventService;

  /**
   * 피보호자의 FK로 피보호자 기념일 정보 리스트를 반환하는 기능
   *
   * @param careNo
   * @return List<EventGetResponseDto>
   */
  @GetMapping
  public ResponseEntity getEventList() {
    try {
      List<EventGetResponseDto> eventGetResponseDtoList = eventService.getAllEvent();
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
   * @return eventGetResponseDto
   */
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
   * 기념일 등록
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

  /**
   * 기념일 수정
   *
   * @param event
   * @return
   */
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

  /**
   * 기념일 삭제
   *
   * @param eventNo
   * @return
   */
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
    return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
  }
}
