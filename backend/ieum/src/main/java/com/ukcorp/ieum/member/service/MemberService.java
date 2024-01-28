package com.ukcorp.ieum.member.service;


import com.ukcorp.ieum.jwt.dto.JwtToken;
import com.ukcorp.ieum.member.dto.MemberLoginRequestDto;
import com.ukcorp.ieum.member.dto.MemberRequestDto;
import com.ukcorp.ieum.member.dto.MemberResponseDto;
import com.ukcorp.ieum.member.entity.Member;

public interface MemberService {

    void signup(MemberRequestDto memberSignupDto);

    JwtToken login(MemberLoginRequestDto loginDto);

    void logout();

    MemberResponseDto getMemberInfo();

    void modifyMember(MemberRequestDto memberModifyDto);

    void withdrawMember();

    Member findById(String memberId);

    boolean isExistsMemberId(String memberId);

    boolean isExistsMemberEmail(String email);

    boolean isExistsMemberPhone(String phone);

    JwtToken refreshAccessToken(String refreshToken);

//    int updateMember(MemberDto member);
}
