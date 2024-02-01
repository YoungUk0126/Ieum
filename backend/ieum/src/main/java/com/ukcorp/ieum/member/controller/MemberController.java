package com.ukcorp.ieum.member.controller;

import com.ukcorp.ieum.jwt.JwtFilter;
import com.ukcorp.ieum.jwt.dto.JwtToken;
import com.ukcorp.ieum.member.dto.*;
import com.ukcorp.ieum.member.service.MemberServiceImpl;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
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
    public ResponseEntity<Map<String, Object>> joinMember(@RequestBody @Valid MemberRequestDto member) {
        memberService.signup(member);

        return handleSuccess("success");
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginMember(@RequestBody @Valid MemberIdPasswordDto loginDto) {
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

    @GetMapping
    public ResponseEntity<Map<String, Object>> getMemberInfo() {
        MemberResponseDto memberInfo = memberService.getMemberInfo();

        return handleSuccess(memberInfo);
    }

    @GetMapping("/logout")
    public ResponseEntity<Map<String, Object>> logoutMember() {
        memberService.logout();

        return handleSuccess("success");
    }


    @PutMapping
    public ResponseEntity<Map<String, Object>> updateMember(@RequestBody @Valid MemberRequestDto member) {
        memberService.modifyMember(member);

        return handleSuccess("success");
    }

    @PutMapping("/password")
    public ResponseEntity<Map<String, Object>> updateMemberPassword(@RequestBody @Valid MemberIdPasswordDto passwordDto) {
        memberService.modifyMemberPassword(passwordDto);

        return handleSuccess("success");
    }

    @DeleteMapping
    public ResponseEntity<Map<String, Object>> withdrawMember() {
        memberService.withdrawMember();

        return handleSuccess("success");
    }

    @PostMapping("/auth")
    public ResponseEntity<Map<String, Object>> sendVerifyCode(@RequestBody @Valid PhoneRequestDto phone) {
        memberService.sendVerifyMessage(phone);

        return handleSuccess("success");
    }

    @PostMapping("/verify")
    public ResponseEntity<Map<String, Object>> verifyCode(@RequestBody @Valid VerifyRequestDto verifyRequestDto) {
        boolean isCorrect = memberService.checkMessageCode(verifyRequestDto);
        if (isCorrect) {
            return handleSuccess("success");
        } else {
            return handleFail("fail");
        }
    }

    @GetMapping("/check-id/{member-id}")
    public ResponseEntity<Map<String, Object>> isDuplicatedId(@PathVariable("member-id") String checkId) {
        boolean isExists = memberService.isExistsMemberId(checkId);
        Map<String, Boolean> response = new HashMap<>();
        if (isExists) {
            // 이미 존재하는 아이디인 경우
            response.put("isDuplicated", true);
        } else {
            // 사용 가능한 아이디인 경우
            response.put("isDuplicated", false);
        }
        return handleSuccess(response);
    }

    @PostMapping("/check-email")
    public ResponseEntity<Map<String, Object>> isDuplicatedEmail(@RequestBody @Valid EmailRequestDto emailDto) {
        boolean isExists = memberService.isExistsMemberEmail(emailDto.getEmail());
        Map<String, Boolean> response = new HashMap<>();
        if (isExists) {
            // 이미 존재하는 이메일인 경우
            response.put("isDuplicated", true);
        } else {
            // 사용 가능한 이메일인 경우
            response.put("isDuplicated", false);
        }
        return handleSuccess(response);
    }

    @PostMapping("/check-phone")
    public ResponseEntity<Map<String, Object>> isDuplicatedPhone(@RequestBody @Valid PhoneRequestDto phoneDto) {
        boolean isExists = memberService.isExistsMemberPhone(phoneDto.getPhone());
        Map<String, Boolean> response = new HashMap<>();
        if (isExists) {
            // 이미 존재하는 핸드폰 번호인 경우
            response.put("isDuplicated", true);
        } else {
            // 사용 가능한 핸드폰 번호인 경우
            response.put("isDuplicated", false);
        }
        return handleSuccess(response);
    }

    @PostMapping("/check-exist")
    public ResponseEntity<Map<String, Object>> isExistMember(@RequestBody @Valid CheckExistDto checkMember) {
        boolean isExists = memberService.checkExistsMember(checkMember);
        Map<String, Boolean> response = new HashMap<>();
        if (isExists) {
            // 이미 존재하는 핸드폰 번호인 경우
            response.put("isExist", true);
        } else {
            // 사용 가능한 핸드폰 번호인 경우
            response.put("isExist", false);
        }
        return handleSuccess(response);
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

    private ResponseEntity<Map<String, Object>> handleFail(Object data) {
        Map<String, Object> result = new HashMap<>();

        result.put("success", false);
        result.put("data", data);
        return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
    }

    private ResponseEntity<Map<String, Object>> handleError(Object data) {
        Map<String, Object> result = new HashMap<>();
        result.put("success", false);
        result.put("data", data);
        return new ResponseEntity<Map<String, Object>>(result, HttpStatus.BAD_REQUEST);
    }

}
