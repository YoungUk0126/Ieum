package com.ukcorp.ieum.iot.controller;

import com.ukcorp.ieum.iot.dto.SerialRequestDto;
import com.ukcorp.ieum.iot.service.IotService;
import com.ukcorp.ieum.jwt.JwtUtil;
import com.ukcorp.ieum.socket.service.SocketService;
import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/devices")
@RequiredArgsConstructor
public class IotController {

  private final IotService iotService;
  private final SocketService socketService;

  @GetMapping("/colseAlert")
  public void sendCloseAlert() {
    iotService.sendCloseAlert();
  }

  @PostMapping("/fire")
  public ResponseEntity<Map<String, Object>> sendFireAlert(@RequestBody SerialRequestDto serial) {
    try {
      iotService.sendFireSms(serial.getSerial());
      return handleSuccess();
    } catch (Exception e) {
      e.printStackTrace();
      return handleError();
    }
  }

  @PostMapping("/check-info")
  public ResponseEntity<Map<String, Object>> checkInfo(@RequestBody SerialRequestDto serial) {
    try {
      iotService.checkInfo(serial.getSerial());
      return handleSuccess();
    } catch (Exception e) {
      e.printStackTrace();
      return handleError();
    }
  }

  @GetMapping("/check-serial/{serial}")
  public ResponseEntity<Map<String, Object>> checkSerial(@PathVariable String serial) {
    try {
      if(iotService.checkSerialCode(serial)){
        return handleSuccess();
      }else{
        return handleError();
      }
    } catch (Exception e) {
      e.printStackTrace();
      return handleError();
    }
  }


  @PostMapping("/check-device")
  public ResponseEntity<?> checkDevice(@RequestBody SerialRequestDto serial) {
    try {
      Map<String, Boolean> result = new HashMap<>();
      if (iotService.activeCheck(serial.getSerial())) {
        result.put("usable", true);
      } else {
        result.put("usable", false);
      }
      return new ResponseEntity<>(result, HttpStatus.OK);
    } catch (Exception e) {
      e.printStackTrace();
      return handleError();
    }
  }

  @GetMapping("/check-status")
  public ResponseEntity<?> checkStatus() {
    try {
      Map<String, String> result = new HashMap<>();

      result.put("result", iotService.activeCheckToken());

      return new ResponseEntity<>(result, HttpStatus.OK);
    } catch (Exception e) {
      e.printStackTrace();
      return handleError();
    }
  }

  /**
   * 작성자 : 이성목
   * <p>
   * 기기 등록하고 활성화 상태로 변경
   *
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
      e.printStackTrace();
      return handleError();
    }
  }

  /**
   * 작성자 : 이성목
   * <p>
   * 시리얼 코드 변경
   *
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
      e.printStackTrace();
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
