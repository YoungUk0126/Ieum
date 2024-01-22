package com.ukcorp.ieum.member.repository;

import com.ukcorp.ieum.member.dto.MemberDto;
import com.ukcorp.ieum.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

}
