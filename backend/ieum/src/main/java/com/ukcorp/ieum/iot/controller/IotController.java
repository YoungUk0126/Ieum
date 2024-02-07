package com.ukcorp.ieum.iot.controller;

import com.ukcorp.ieum.iot.dto.SerialRequestDto;
import com.ukcorp.ieum.iot.service.IotService;
import com.ukcorp.ieum.jwt.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

    @PostMapping("/check")
    public ResponseEntity<Map<String, Object>> checkDevice(@RequestBody SerialRequestDto serial) {
        try {
            iotService.activeCheck(serial.getSerial());
            return handleSuccess();
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
