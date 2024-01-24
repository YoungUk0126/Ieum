package com.ukcorp.ieum.care.controller;

import com.ukcorp.ieum.care.dto.request.CareInsertRequestDto;
import com.ukcorp.ieum.care.dto.request.CareUpdateRequestDto;
import com.ukcorp.ieum.care.service.CareServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/care")
public class CareController {

    private final CareServiceImpl careService;

    @GetMapping("/{care-no}")
    private ResponseEntity<Map<String, Object>> getCareInfo(@PathVariable("care-no") Long careNo) {

        return null;
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
