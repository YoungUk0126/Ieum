package com.ukcorp.ieum.member.service;


import com.ukcorp.ieum.member.dto.MemberDto;
import com.ukcorp.ieum.member.entity.Member;

public interface MemberService {

    int joinMember(MemberDto member);
    int modifyMember(MemberDto member);
    int deleteMember(String memberId);
    Member findById(String memberId);

    Member loginMember(String loginId, String loginPassword);

    int updateMember(MemberDto member);
}
