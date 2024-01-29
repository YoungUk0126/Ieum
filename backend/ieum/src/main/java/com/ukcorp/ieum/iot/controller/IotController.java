package com.ukcorp.ieum.iot.controller;

import com.ukcorp.ieum.iot.service.IotService;
import com.ukcorp.ieum.jwt.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/devices")
@RequiredArgsConstructor
public class IotController {

  private final IotService iotService;

  @PostMapping
  public ResponseEntity postIotDevice(@RequestBody String code) {

    String userId = JwtUtil.getMemberId().get();
    try {
      iotService.registSerialCode(code, userId);
      return handleSuccess();
    } catch (Exception e) {
      return handleError();
    }
  }

  @PutMapping
  public ResponseEntity putIotDevice(@RequestBody String code) {
    // TODO : 이음 코드 수정

    String userId = JwtUtil.getMemberId().get();

    try {
      iotService.updateSerialCode(code, userId);
      return handleSuccess();
    } catch (Exception e) {
      return handleError();
    }
  }

  private ResponseEntity<Map<String, Object>> handleSuccess() {
    Map<String, Object> result = new HashMap<>();
    result.put("success", true);
    return new ResponseEntity<>(result, HttpStatus.OK);
  }

  private ResponseEntity<Map<String, Object>> handleError() {
    Map<String, Object> result = new HashMap<>();
    result.put("success", false);
    return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
  }

}
