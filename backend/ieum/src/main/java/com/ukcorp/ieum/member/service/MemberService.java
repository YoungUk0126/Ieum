package com.ukcorp.ieum.member.service;


import com.ukcorp.ieum.jwt.dto.JwtToken;
import com.ukcorp.ieum.member.dto.LoginDto;
import com.ukcorp.ieum.member.dto.MemberDto;
import com.ukcorp.ieum.member.entity.Member;

public interface MemberService {

    void signup(MemberDto memberSignupDto);

    JwtToken login(LoginDto loginDto);

    int modifyMember(MemberDto memberModifyDto);

    int deleteMember(String memberId);

    Member findById(String memberId);

    boolean isExistsMemberId(String memberId);

    JwtToken refreshAccessToken(String refreshToken);

//    int updateMember(MemberDto member);
}
