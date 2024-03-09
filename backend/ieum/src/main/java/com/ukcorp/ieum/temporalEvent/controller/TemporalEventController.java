package com.ukcorp.ieum.temporalEvent.controller;

import com.ukcorp.ieum.temporalEvent.dto.request.TemporalEventInsertRequestDto;
import com.ukcorp.ieum.temporalEvent.dto.request.TemporalEventUpdateRequestDto;
import com.ukcorp.ieum.temporalEvent.dto.response.TemporalEventResponseDto;
import com.ukcorp.ieum.temporalEvent.service.TemporalEventService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/event")
@RequiredArgsConstructor
public class TemporalEventController {

  private final TemporalEventService temporalEventService;

  /**
   * 일정 관련 리스트 조회
   *
   * @param
   * @return List<TemporalEventDto>
   */
  @GetMapping
  public ResponseEntity<Map<String, Object>> getEvent() {
    try {
      List<TemporalEventResponseDto> list = temporalEventService.getList();
      return handleSuccess(list);
    } catch (Exception exception) {
      log.debug(exception.getMessage());
      return handleFail("Fail");
    }
  }

  /**
   * 일정 관련 리스트 조회
   *
   * @param
   * @return List<TemporalEventDto>
   */
  @GetMapping("/{year}/{month}")
  public ResponseEntity<Map<String, Object>> getEventMonth(@PathVariable("year") int year,@PathVariable("month") int month) {
    try {
      List<TemporalEventResponseDto> list = temporalEventService.getListMonth(year, month);
      return handleSuccess(list);
    } catch (Exception exception) {
      log.debug(exception.getMessage());
      return handleFail("Fail");
    }
  }

  /**
   * 일정 관련 상세 정보(개별) 조회
   *
   * @param eventNo
   * @return TemporalEventDto
   */
  @GetMapping("/detail/{eventNo}")
  public ResponseEntity<Map<String, Object>> getEventDetail(@PathVariable("eventNo") Long eventNo) {
    try {
      TemporalEventResponseDto event = temporalEventService.getDetail(eventNo);
      return handleSuccess(event);
    } catch (Exception exception) {
      log.debug(exception.getMessage());
      return handleFail("Fail");
    }
  }

  /**
   * 일정 삭제
   *
   * @param eventNo
   * @return
   */
  @DeleteMapping("/{eventNo}")
  public ResponseEntity<Map<String, Object>> deleteEvent(@PathVariable("eventNo") Long eventNo) {
    try {
      temporalEventService.deleteEvent(eventNo);
      return handleSuccess("");
    } catch (Exception e) {
      log.debug(e.getMessage());
      return handleFail("Fail");
    }
  }

  /**
   * 일정 등록
   *
   * @param event
   * @return
   */
  @PostMapping
  public ResponseEntity<Map<String, Object>> postsEvent(@RequestBody TemporalEventInsertRequestDto event) {
    try {
      temporalEventService.registEvent(event);
      return handleSuccess("");
    } catch (Exception e) {
      log.debug(e.getMessage());
      return handleFail("Fail");
    }
  }

  /**
   * 일정 수정
   *
   * @param event
   * @return
   */
  @PutMapping
  public ResponseEntity<Map<String, Object>> putsEvent(@RequestBody TemporalEventUpdateRequestDto event) {
    try {
      temporalEventService.modifyEvent(event);
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
