package com.ukcorp.ieum.member.service;

import com.ukcorp.ieum.jwt.dto.JwtToken;
import com.ukcorp.ieum.member.dto.*;

public interface MemberService {

    void signup(MemberRequestDto memberSignupDto);

    JwtToken login(MemberIdPasswordDto loginDto);

    void logout();

    MemberResponseDto getMemberInfo();

    void modifyMember(MemberRequestDto memberModifyDto);

    void modifyMemberPassword(MemberIdPasswordDto passwordDto);

    void withdrawMember();

    boolean isExistsMemberId(String memberId);

    boolean isExistsMemberEmail(String email);

    boolean isExistsMemberPhone(String phone);

    boolean checkExistsMember(CheckExistDto checkMember);

    void sendVerifyMessage(PhoneRequestDto phoneRequestDto);

    boolean checkMessageCode(VerifyRequestDto verifyRequestDto);

    JwtToken refreshAccessToken(String refreshToken);
}
