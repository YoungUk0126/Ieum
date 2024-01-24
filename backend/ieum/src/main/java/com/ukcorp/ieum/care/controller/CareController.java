package com.ukcorp.ieum.care.controller;

import com.ukcorp.ieum.care.dto.request.CareInsertRequestDto;
import com.ukcorp.ieum.care.dto.request.CareUpdateRequestDto;
import com.ukcorp.ieum.care.dto.response.CareGetResponseDto;
import com.ukcorp.ieum.care.service.CareServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/care")
@Slf4j
public class CareController {

    private final CareServiceImpl careService;

    /**
     * 피보호자의 PK로 피보호자 정보를 반환하는 기능
     * @param careNo
     */
    @GetMapping("/{care-no}")
    private ResponseEntity<Map<String, Object>> getCareInfo(@PathVariable("care-no") Long careNo) {
        try {
            CareGetResponseDto careDto = careService.getCareInfo(careNo);
            return handleSuccess(careDto);

        } catch (Exception e) {
            log.debug(e.getMessage());
            return handleError("Fail");
        }
    }

    @PostMapping
    private ResponseEntity<Map<String, Object>> insertCareInfo(CareInsertRequestDto careDto) {

        return null;
    }

    @PutMapping
    private ResponseEntity<Map<String, Object>> updateCareInfo(CareUpdateRequestDto careDto) {

        return null;
    }

    @DeleteMapping("/{care-no}")
    private ResponseEntity<Map<String, Object>> deleteCareInfo(@PathVariable("care-no") Long careNo) {

        return null;
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
