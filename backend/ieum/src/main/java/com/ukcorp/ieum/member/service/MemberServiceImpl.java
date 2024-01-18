package com.ukcorp.ieum.member.service;

import com.ukcorp.ieum.member.dto.MemberDto;
import com.ukcorp.ieum.member.entity.Member;
import com.ukcorp.ieum.member.repository.MemberRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MemberServiceImpl implements MemberService{

    private final MemberRepository repo;
//    mapper
    @Override
    public int joinMember(MemberDto member) {
//        mapper를 써서 Entity로 바꾸고 save(entity)
//        return repo.save(member);
        return 0;
    }

    @Override
    public int modifyMember(MemberDto member) {
        return 0;
    }

    @Override
    public int deleteMember(String memberId) {
        Member member = repo.findByMemberId(memberId);
//        JPA는 delete하면 return으로 int가 안나오나?
//        return repo.delete(member);
        return 0;
    }

    @Override
    public Member findById(String memberId) {
        return null;
    }

    @Override
    public Member loginMember(String loginId, String loginPassword) {
        return repo.findByMemberIdAndPassword(loginId, loginPassword);
    }

    @Override
    public int updateMember(MemberDto member) {
//        member mapper로 Entity 변환 후 save에 넣고 리턴
//        return repo.save(member);
        return 0;
    }
}
