package com.ukcorp.ieum.member.service;

import com.ukcorp.ieum.jwt.MemberDetails;
import com.ukcorp.ieum.member.entity.Member;
import com.ukcorp.ieum.member.repository.MemberRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public MemberDetails loadUserByUsername(String memberId) throws UsernameNotFoundException {
        Member loginMember = memberRepository.findByMemberId(memberId)
                .orElseThrow(() -> new RuntimeException("NOT FOUND MEMBER"));
        log.info("DB에서 꺼낸 멤버 >> " + loginMember.getMemberId());
        return memberRepository.findByMemberId(memberId)
                .map(this::createMemberDetail)
                .orElseThrow(() -> new UsernameNotFoundException("해당하는 회원을 찾을 수 없습니다."));
    }

    // 해당하는 User 의 데이터가 존재한다면 UserDetails 객체로 만들어서 return
    private MemberDetails createMemberDetail(Member member) {
        Set<GrantedAuthority> grantedAuthorities = member.getAuthorities().stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toSet());

        log.info("토큰 생성할 때의 User 권한 >> " + grantedAuthorities);

        log.info("DB 멤버 비밀번호 >> " + member.getMemberPassword());
        log.info("로그인할 때 저장하는 careNo >> " + member.getCareInfo().getCareNo());
        return MemberDetails.builder()
                .username(member.getMemberId())
                .password(member.getMemberPassword())
                .authorities(grantedAuthorities)
                .careNo(member.getCareInfo().getCareNo()).build();

    }
}
