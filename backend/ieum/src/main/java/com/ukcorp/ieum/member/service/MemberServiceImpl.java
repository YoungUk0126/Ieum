package com.ukcorp.ieum.member.service;

import com.ukcorp.ieum.jwt.TokenProvider;
import com.ukcorp.ieum.jwt.dto.JwtToken;
import com.ukcorp.ieum.member.dto.LoginDto;
import com.ukcorp.ieum.member.dto.MemberDto;
import com.ukcorp.ieum.member.entity.Member;
import com.ukcorp.ieum.member.mapper.MemberMapper;
import com.ukcorp.ieum.member.repository.MemberRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final MemberMapper memberMapper;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final TokenProvider tokenProvider;

    /**
     * 회원가입
     *
     * @param memberSignupDto
     */
    @Override
    public void signup(MemberDto memberSignupDto) {
        Member newMember = memberMapper.memberDtoToMember(memberSignupDto);
        newMember.setAuthorities();
        memberRepository.save(newMember);
    }

    /**
     * 로그인
     *
     * @param loginDto
     * @return
     */
    @Override
    public JwtToken login(LoginDto loginDto) {
        // username + password 를 기반으로 Authentication 객체 생성
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginDto.getMemberId(), loginDto.getPassword());

        // 검증 진행 (MemberDetailsService의 loadUserByUsername 메서드 실행
        Authentication authentication = authenticationManagerBuilder
                .getObject().authenticate(authenticationToken);

        // 인증 정보 기반으로 Token 생성
        return tokenProvider.createToken(authentication);
    }

    @Override
    public int modifyMember(MemberDto member) {
        return 0;
    }


    @Override
    public int deleteMember(String memberId) {
//        Member member = repo.findByMemberId(memberId);
//        JPA는 delete하면 return으로 int가 안나오나?
//        return repo.delete(member);
        return 0;
    }

    @Override
    public Member findById(String memberId) {
        return null;
    }


//    @Override
//    public int updateMember(MemberDto member) {
////        member mapper로 Entity 변환 후 save에 넣고 리턴
////        return repo.save(member);
//        return 0;
//    }
}
