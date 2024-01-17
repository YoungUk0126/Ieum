package com.ukcorp.ieum.care.controller;

import com.ukcorp.ieum.care.dto.CareRequestDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/care")
public class CareController {

    @GetMapping("/{care-id}")
    public ResponseEntity getCareInfo(@PathVariable("care-id") Long careId) {
        // TODO : 피보호자 정보 조회 로직 추가
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity postCareInfo(@RequestBody CareRequestDto careRequestDto) {
        // TODO : 피보호자 정보 등록 로직 추가
        return new ResponseEntity(HttpStatus.OK);
    }

    // TODO : 피보호자 정보 수정
    // TODO : 피보호자 정보 삭제
}
