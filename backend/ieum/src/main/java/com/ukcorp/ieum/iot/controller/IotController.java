package com.ukcorp.ieum.iot.controller;

import com.ukcorp.ieum.iot.dto.request.CheckRequestDto;
import com.ukcorp.ieum.iot.service.IotService;
import com.ukcorp.ieum.jwt.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.Check;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/devices")
@RequiredArgsConstructor
public class IotController {

  private final IotService iotService;


  /**
   * 작성자 : 이성목
   *
   * 기기로부터 엔드포인트 정보를 받아서 저장
   * @param checkRequestDto
   * @return
   */
  @PostMapping("/iot/endpoint")
  public ResponseEntity updateEndpoint(@RequestBody CheckRequestDto checkRequestDto) {
    try {
      iotService.updateEndPoint(checkRequestDto.getCode(), checkRequestDto.getEndPoint());
      return handleSuccess();
    } catch (Exception e) {
      return handleError();
    }
  }

  /**
   * 작성자 : 이성목
   *
   * 기기 등록하고 활성화 상태로 변경
   * @param code
   * @return
   */
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

  /**
   * 작성자 : 이성목
   *
   * 시리얼 코드 변경
   * @param code
   * @return
   */
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
    return new ResponseEntity<>(result, HttpStatus.OK);
  }

}
