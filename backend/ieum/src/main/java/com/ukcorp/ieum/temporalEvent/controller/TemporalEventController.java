package com.ukcorp.ieum.schedule.controller;

import com.ukcorp.ieum.schedule.dto.TemporalEventDto;
import com.ukcorp.ieum.schedule.service.TemporalEventService;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/schedule")
@RequiredArgsConstructor
public class TemporalEventController {

    private final TemporalEventService temporalEventService;

    public  ResponseEntity<Map<String, Object>> getEvent(@RequestParam Long careNo){
      try{
        List<TemporalEventDto> list = temporalEventService.getList(careNo);
        return handleSuccess(list);
      }catch (Exception exception){
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
