package com.ukcorp.ieum.pill.controller;

import com.ukcorp.ieum.pill.dto.request.PillInfoRequestDto;
import com.ukcorp.ieum.pill.service.PillServiceImpl;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/pill")
@AllArgsConstructor
@Slf4j
public class PillController {


    final PillServiceImpl pillService;
    @GetMapping("/")
    public ResponseEntity<Map<String, Object>> getPillList() {

        return null;
    }

    @GetMapping("/{pill-info-id}")
    public ResponseEntity<Map<String, Object>> getPill(@PathVariable ("pill-info-id") Long id) {
        return null;
    }

    /**
     * @author : 김영욱
     * 약 정보를 받기 위한 Controller
     */
    @PostMapping("/")
    public ResponseEntity<Map<String, Object>> insertPill(@RequestBody PillInfoRequestDto pillInfo, @RequestParam(value = "pill_date[]") List<String> pillTimeDate, @RequestParam(value = "pill_time[]") List<String> pillTimeTime) {
        log.debug("==============약 정보 등록 시작===============");
        int result = pillService.insertPill(pillInfo, pillTimeDate, pillTimeTime);

        if(result != 0){
            return handleSuccess(result);
        }
        else {
            return handleError(result);
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
