package com.ukcorp.ieum.member.repository;

import com.ukcorp.ieum.member.dto.MemberDto;
import com.ukcorp.ieum.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {


    @Query("select m from Member m where m.memberId=:memberId")
    Member findByMemberId(String memberId);

//    로그인용
    Member findByMemberIdAndPassword(String loginId, String loginPassword);
}
