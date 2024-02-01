package com.ukcorp.ieum.member.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ukcorp.ieum.jwt.dto.JwtToken;
import com.ukcorp.ieum.member.dto.*;

import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public interface MemberService {

    void signup(MemberRequestDto memberSignupDto);

    JwtToken login(MemberLoginRequestDto loginDto);

    void logout();

    MemberResponseDto getMemberInfo();

    void modifyMember(MemberRequestDto memberModifyDto);

    void modifyMemberPassword(String memberPassword);

    void withdrawMember();

    boolean isExistsMemberId(String memberId);

    boolean isExistsMemberEmail(String email);

    boolean isExistsMemberPhone(String phone);

    boolean checkExistsMember(CheckExistDto checkMember);

    void sendVerifyMessage(PhoneRequestDto phoneRequestDto)
            throws UnsupportedEncodingException, NoSuchAlgorithmException, URISyntaxException, InvalidKeyException, JsonProcessingException;

    boolean checkMessageCode(VerifyRequestDto verifyRequestDto);

    JwtToken refreshAccessToken(String refreshToken);
}
