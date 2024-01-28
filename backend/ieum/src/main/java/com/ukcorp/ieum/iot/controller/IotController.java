package com.ukcorp.ieum.iot.controller;

import com.ukcorp.ieum.iot.service.IotService;
import com.ukcorp.ieum.jwt.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/devices")
@RequiredArgsConstructor
public class IotController {

    private final IotService iotService;

    @PostMapping
    public ResponseEntity postIotDevice(@RequestBody String code) {

        String userId = JwtUtil.getUserId().get();

        iotService.registSerialCode(code, userId);

        return new ResponseEntity(HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity putIotDevice(@RequestBody String code) {

        String userId = JwtUtil.getUserId().get();

        iotService.updateSerialCode(code, userId);

        return new ResponseEntity(HttpStatus.OK);
    }
    // TODO : 이음 코드 수정
}
