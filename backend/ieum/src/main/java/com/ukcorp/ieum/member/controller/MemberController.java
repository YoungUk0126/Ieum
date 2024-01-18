package com.ukcorp.ieum.member.controller;

import com.ukcorp.ieum.member.dto.LoginDto;
import com.ukcorp.ieum.member.dto.MemberDto;
import com.ukcorp.ieum.member.entity.Member;
import com.ukcorp.ieum.member.service.MemberServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/member")
@AllArgsConstructor
public class MemberController {

    private final MemberServiceImpl memberService;
    @PostMapping("/join")
    public ResponseEntity<Map<String, Object>> joinMember(@RequestBody MemberDto member) {

//        result에 service 결과 받아올 것
        int result = memberService.joinMember(member);

        if(result != 1) return handleError(result);
        else return handleSuccess(result);
    }
    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> loginMember(@RequestBody LoginDto loginForm){
        Member result = memberService.loginMember(loginForm.getMemberId(), loginForm.getMemberId());

        if(result != null) {
            return handleSuccess(result);
        }
        else {
            return handleError(result);
        }
    }

    @PutMapping("/modify")
    public ResponseEntity<Map<String, Object>> updateMember(@RequestBody MemberDto member) {
        int result = memberService.updateMember(member);

        if(result != 0) {
            return handleSuccess(result);
        }
        else {
            return handleError(result);
        }
    }

    @DeleteMapping("/{member-id}")
    public ResponseEntity<Map<String, Object>> deleteMember(@PathVariable("member-id") String memberId){
        int result = memberService.deleteMember(memberId);

        if(result != 0) {
            return handleSuccess(result);
        }
        else {
            return handleError(result);
        }
    }


    @GetMapping("/check-id/{member-id}")
    public ResponseEntity<Map<String, Object>> searchMemberById(@PathVariable("member-id")String checkId){
        Member result = memberService.findById(checkId);
//        이미 아이디가 존재한다면
        if(result != null){
            return handleError(0);
        }
//        아이디가 없다면
        else {
            return handleSuccess(1);
        }
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
