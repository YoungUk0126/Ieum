package com.ukcorp.ieum.member.controller;

import com.ukcorp.ieum.member.dto.MemberDto;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/member")
@AllArgsConstructor
public class MemberController {

//    final MemberServiceimpl memberService;
    @PostMapping("/join")
    public ResponseEntity<Map<String, Object>> joinMember(@RequestBody MemberDto member) {

//        result에 service 결과 받아올 것
        int result = 1;

        if(result != 1) return handleError(result);
        else return handleSuccess(result);
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
        return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
    }

}
