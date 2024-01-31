package com.ukcorp.ieum.member.controller;

import com.ukcorp.ieum.jwt.JwtFilter;
import com.ukcorp.ieum.jwt.JwtUtil;
import com.ukcorp.ieum.jwt.dto.JwtToken;
import com.ukcorp.ieum.member.dto.LoginDto;
import com.ukcorp.ieum.member.dto.MemberDto;
import com.ukcorp.ieum.member.entity.Member;
import com.ukcorp.ieum.member.service.MemberServiceImpl;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/member")
@RequiredArgsConstructor
@Slf4j
public class MemberController {

    private final MemberServiceImpl memberService;

    @PostMapping("/join")
    public ResponseEntity joinMember(@Valid @RequestBody MemberDto member) {

        memberService.signup(member);

        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginMember(@RequestBody MemberLoginRequestDto loginDto) {
        try {
            JwtToken jwtToken = memberService.login(loginDto);

            // Header에  토큰 설정
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.add(JwtFilter.ACCESS_TOKEN_HEADER, "Bearer " + jwtToken.getAccessToken());
            httpHeaders.add(JwtFilter.REFRESH_TOKEN_HEADER, "Bearer " + jwtToken.getRefreshToken());

            return new ResponseEntity<>(jwtToken, httpHeaders, HttpStatus.OK);
        } catch (UsernameNotFoundException e) {
            return new ResponseEntity<>("찾을 수 없는 회원", HttpStatus.BAD_REQUEST);
        } catch (BadCredentialsException e) {
            return new ResponseEntity<>("비밀번호 틀림", HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/modify")
    public ResponseEntity<Map<String, Object>> updateMember(@RequestBody MemberDto member) {
        int result = memberService.modifyMember(member);

        if (result != 0) {
            return handleSuccess(result);
        } else {
            return handleError(result);
        }
    }

    @DeleteMapping("/{member-id}")
    public ResponseEntity<Map<String, Object>> deleteMember(@PathVariable("member-id") String memberId) {
        int result = memberService.deleteMember(memberId);

        if (result != 0) {
            return handleSuccess(result);
        } else {
            return handleError(result);
        }
    }


    @GetMapping("/check-id/{member-id}")
    public ResponseEntity<Map<String, Object>> searchMemberById(@PathVariable("member-id") String checkId) {
        Member result = memberService.findById(checkId);
//        이미 아이디가 존재한다면
        if (result != null) {
            return handleError(0);
        }
//        아이디가 없다면
        else {
            return handleSuccess(1);
        }
    }

    @PostMapping("/refresh")
    private ResponseEntity<?> refreshAccessToken(@RequestBody RefreshRequestDto refreshToken) {
        log.info("controller 들어온 값 >> "+ refreshToken.getRefreshToken());
        try {
            JwtToken jwtToken = memberService.refreshAccessToken(refreshToken.getRefreshToken());

            // Header에  토큰 설정
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.add(JwtFilter.ACCESS_TOKEN_HEADER, "Bearer " + jwtToken.getAccessToken());
            httpHeaders.add(JwtFilter.REFRESH_TOKEN_HEADER, "Bearer " + jwtToken.getRefreshToken());

            return new ResponseEntity<>(jwtToken, httpHeaders, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return handleFail("실패");
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
