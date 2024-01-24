package com.ukcorp.ieum.api.controller;

import com.ukcorp.ieum.api.service.ChatGPTService;
import com.ukcorp.ieum.api.service.GoogleService;
import com.ukcorp.ieum.api.service.NaverService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/ieum")
public class IeumAPIController {
  private final GoogleService googleService;
  private final NaverService naverService;
  private final ChatGPTService chatGPTService;


  private ResponseEntity<Map<String,Object>> handleSuccess(Object data){
    Map<String, Object> result = new HashMap<>();
    result.put("success", true);
    result.put("data", data);
    return new ResponseEntity<Map<String,Object>>(result, HttpStatus.OK);
  }

  private ResponseEntity<Map<String,Object>> handleFail(Object data){
    Map<String, Object> result = new HashMap<>();
    result.put("success", false);
    result.put("data", data);
    return new ResponseEntity<Map<String,Object>>(result, HttpStatus.OK);
  }

}
