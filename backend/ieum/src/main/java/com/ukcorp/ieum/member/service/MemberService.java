package com.ukcorp.ieum.member.service;


import com.ukcorp.ieum.jwt.dto.JwtToken;
import com.ukcorp.ieum.member.dto.LoginDto;
import com.ukcorp.ieum.member.dto.MemberRequestDto;
import com.ukcorp.ieum.member.entity.Member;

public interface MemberService {

    void signup(MemberRequestDto memberSignupDto);

    JwtToken login(LoginDto loginDto);

    int modifyMember(MemberRequestDto memberModifyDto);

    int deleteMember(String memberId);

    Member findById(String memberId);

    boolean isExistsMemberId(String memberId);

    boolean isExistsMemberEmail(String email);

    JwtToken refreshAccessToken(String refreshToken);

//    int updateMember(MemberDto member);
}
