package com.ukcorp.ieum.member.repository;

import com.ukcorp.ieum.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByMemberId(String memberId);

    boolean existsByMemberId(String memberId);

    boolean existsByMemberEmail(String email);

    boolean existsByMemberPhone(String phone);

    // memberId와 memberPhone이 일치하는 회원 찾기
    boolean existsByMemberIdAndMemberPhone(String memberId, String memberPhone);

    //    로그인용
    Member findByMemberIdAndMemberPassword(String loginId, String loginPassword);
}
