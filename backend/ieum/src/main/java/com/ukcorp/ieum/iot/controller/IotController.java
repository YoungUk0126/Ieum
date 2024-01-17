package com.ukcorp.ieum.iot.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/devices")
public class IotController {

    @PostMapping
    public ResponseEntity postIotDevice(@RequestBody String code) {
        // TODO : 이음이 코드 등록 로직 추가
        return new ResponseEntity(HttpStatus.OK);
    }

    // TODO : 이음 코드 수정
}
