package com.ukcorp.ieum.pill.controller;

import com.ukcorp.ieum.meal.dto.response.MealGetResponseDto;
import com.ukcorp.ieum.pill.dto.request.PillInfoInsertRequestDto;
import com.ukcorp.ieum.pill.dto.request.PillInfoUpdateRequestDto;
import com.ukcorp.ieum.pill.dto.response.PillInfoGetResponseDto;
import com.ukcorp.ieum.pill.dto.response.TotalPillGetResponseDto;
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

    private final PillServiceImpl pillService;
    /**
     * @author : 김영욱
     * 피보호자의 PK를 받아 피보호자와 관련된
     * 약 정보들을 받기 위한 Controller
     */
    @GetMapping("/{care-no}")
    public ResponseEntity<Map<String, Object>> getPillList(Long careNo) {
        try{
            List<TotalPillGetResponseDto> result = pillService.getAllPillInfo(careNo);
            return handleSuccess(result);
        } catch (Exception e){
            log.debug(e.getMessage());
            return handleError("Fail");
        }
    }

    /**
     * @author : 김영욱
     * 약 PK를 받아 약 상세 정보를 받기 위한 Controller
     */
    @GetMapping("/detail/{pill-info-id}")
    public ResponseEntity<Map<String, Object>> getPill(@PathVariable ("pill-info-id") Long pillId) {
        try {
            TotalPillGetResponseDto result = pillService.getPillInfo(pillId);
            return handleSuccess(result);
        } catch (Exception e) {
            log.debug(e.getMessage());
            return handleError("Fail");
        }
    }

    /**
     * @author : 김영욱
     * 약 정보를 넣기 위한 Controller
     */
    @PostMapping("/")
    public ResponseEntity<Map<String, Object>> insertPill(@RequestBody PillInfoInsertRequestDto pillInfo) {
        log.debug("==============약 정보 등록 시작===============");
        try {
            pillService.insertPill(pillInfo);
            return handleSuccess("");
        } catch (Exception e) {
            log.debug(e.getMessage());
            return handleError("Fail");
        }
    }

    @PutMapping("/")
    public ResponseEntity<Map<String, Object>> updatePill(@RequestBody PillInfoUpdateRequestDto pillInfo) {
        log.debug("==============약 정보 수정 시작===============");
        try {
            pillService.updatePill(pillInfo);
            return handleSuccess("");
        } catch (Exception e) {
            log.debug(e.getMessage());
            return handleError("Fail");
        }
    }

    @DeleteMapping("/{pill-info-no}")
    public ResponseEntity<Map<String, Object>> deletePillInfo(@PathVariable("pill-info-no") Long pillInfoNo) {
        log.debug("==============약 정보 삭제 시작===============");
        try {
            pillService.deletePillInfo(pillInfoNo);
            return handleSuccess("");
        } catch (Exception e) {
            log.debug(e.getMessage());
            return handleError("Fail");
        }
    }

    @DeleteMapping("/time/{pill-time-no}")
    public ResponseEntity<Map<String, Object>> deletePillTime(@PathVariable("pill-time-no") Long pillTimeNo) {
        log.debug("==============약 시간 정보 삭제 시작===============");
        try {
            pillService.deletePillTime(pillTimeNo);
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
