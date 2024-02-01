package com.ukcorp.ieum.member.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ukcorp.ieum.Sms.ValidationUtil;
import com.ukcorp.ieum.api.dto.MessageDTO;
import com.ukcorp.ieum.api.service.NaverService;
import com.ukcorp.ieum.care.entity.CareInfo;
import com.ukcorp.ieum.care.repository.CareRepository;
import com.ukcorp.ieum.jwt.JwtUtil;
import com.ukcorp.ieum.jwt.MemberDetails;
import com.ukcorp.ieum.jwt.TokenProvider;
import com.ukcorp.ieum.jwt.dto.JwtToken;
import com.ukcorp.ieum.member.dto.*;
import com.ukcorp.ieum.member.entity.Member;
import com.ukcorp.ieum.member.mapper.MemberMapper;
import com.ukcorp.ieum.member.repository.MemberRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class MemberServiceImpl implements MemberService {

    private final NaverService naverService;
    private final MemberRepository memberRepository;
    private final CareRepository careRepository;
    private final ValidationUtil validationUtil;
    private final MemberMapper memberMapper;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final TokenProvider tokenProvider;
    private final PasswordEncoder passwordEncoder;

    /**
     * 회원가입
     *
     * @param memberSignupDto
     */
    @Override
    @Transactional
    public void signup(MemberRequestDto memberSignupDto) {
        Member newMember = memberMapper.memberRequestDtoToMember(memberSignupDto);
        CareInfo careInfo = CareInfo.builder().build();
        CareInfo savedCareInfo = careRepository.save(careInfo);
        newMember.setNewMember(savedCareInfo);
        memberRepository.save(newMember);
    }

    /**
     * 로그인
     *
     * @param loginDto
     * @return
     */
    @Override
    @Transactional
    public JwtToken login(MemberIdPasswordDto loginDto) throws UsernameNotFoundException {
        // username + password 를 기반으로 Authentication 객체 생성
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginDto.getMemberId(), loginDto.getPassword());

        // 검증 진행 (MemberDetailsService의 loadUserByUsername 메서드 실행
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        // 인증 정보 기반으로 Token 생성
        return tokenProvider.createToken(authentication);
    }

    /**
     * 로그아웃
     */
    @Override
    @Transactional
    public void logout() {
        String logoutMember = JwtUtil.getMemberId()
                .orElseThrow(() -> new NoSuchElementException("MEMBER NOT FOUND"));

        // 로그아웃 시 Redis에서 RefreshToken 삭제
        tokenProvider.deleteRefreshTokenFromRedis(logoutMember);
    }

    /**
     * 회원 정보 조회
     *
     * @return 회원 정보
     */
    @Override
    public MemberResponseDto getMemberInfo() {
        String memberId = JwtUtil.getMemberId().orElseThrow(()
                -> new NoSuchElementException("MEMBER NOT FOUND"));
        Member member = memberRepository.findByMemberId(memberId)
                .orElseThrow(() -> new NoSuchElementException("MEMBER NOT FOUND"));
        return memberMapper.memberToMemberResponseDto(member);
    }

    /**
     * 회원 수정
     *
     * @param member
     */
    @Override
    @Transactional
    public void modifyMember(MemberRequestDto member) {
        String memberId = JwtUtil.getMemberId()
                .orElseThrow(() -> new NoSuchElementException("MEMBER NOT FOUND"));
        if (member.getMemberId().equals(member.getMemberId())) {
            // 로그인한 사람이랑 정보가 일치할 떄만 업데이트
            Member updateMember = memberRepository.findByMemberId(memberId)
                    .orElseThrow(() -> new NoSuchElementException("MEMBER NOT FOUND"));

            updateMember.updateMember(member);
        }
    }

    @Override
    @Transactional
    public void modifyMemberPassword(MemberIdPasswordDto passwordDto) {
        Member member = memberRepository.findByMemberId(passwordDto.getMemberId())
                .orElseThrow(() -> new UsernameNotFoundException("MEMBER NOT FOUND"));
        member.updatePassword(passwordEncoder.encode(passwordDto.getPassword()));
    }

    /**
     * 회원 탈퇴
     */
    @Override
    @Transactional
    public void withdrawMember() {
        String memberId = JwtUtil.getMemberId()
                .orElseThrow(() -> new NoSuchElementException("MEMBER NOT FOUND"));
        Member member = memberRepository.findByMemberId(memberId)
                .orElseThrow(() -> new NoSuchElementException("MEMBER NOT FOUND"));
        member.withdrawMember();
    }

    /**
     * 아이디 중복 확인
     *
     * @param memberId
     * @return 중복 여부
     */
    @Override
    public boolean isExistsMemberId(String memberId) {
        return memberRepository.existsByMemberId(memberId);
    }

    /**
     * 이메일 중복 확인
     *
     * @param email
     * @return 중복 여부
     */
    @Override
    public boolean isExistsMemberEmail(String email) {
        return memberRepository.existsByMemberEmail(email);
    }

    /**
     * 핸드폰 중복 확인
     *
     * @param phone
     * @return 중복 여부
     */
    @Override
    public boolean isExistsMemberPhone(String phone) {
        return memberRepository.existsByMemberPhone(phone);
    }

    @Override
    public boolean checkExistsMember(CheckExistDto checkMember) {
        return memberRepository
                .existsByMemberIdAndMemberPhone(checkMember.getMemberId(), checkMember.getPhone());
    }

    /**
     * 핸드폰 인증 코드 발송
     *
     * @param phoneRequestDto
     */
    @Override
    public void sendVerifyMessage(PhoneRequestDto phoneRequestDto) throws UnsupportedEncodingException, NoSuchAlgorithmException, URISyntaxException, InvalidKeyException, JsonProcessingException {
        String phone = phoneRequestDto.getPhone().replaceAll("-", "");
        String code = validationUtil.createRandomCode();
        MessageDTO sendSms = MessageDTO.builder().to(phone)
                .content("[이음] 아래의 인증번호를 입력해주세요\n" + code).build();
        log.info("보내는 번호 >> " + phone);
        log.info("보내는 코드 >> " + code);
        naverService.sendSms(sendSms);
        validationUtil.registCode(phone, code);
    }

    /**
     * 문자 인증 코드 검증
     *
     * @param verifyRequestDto
     * @return 검증 여부
     */
    @Override
    public boolean checkMessageCode(VerifyRequestDto verifyRequestDto) {
        String phone = verifyRequestDto.getPhone().replaceAll("-", "");
        return validationUtil.verifyCode(phone, verifyRequestDto.getCode());
    }

    /**
     * RefreshToken으로 Token 재발급
     *
     * @param refreshToken
     * @return JwtToken(AccessToken + RefreshToken)
     */
    @Override
    @Transactional
    public JwtToken refreshAccessToken(String refreshToken) {
        // 유저 정보 가져오기
        String memberId = tokenProvider.getMemberId(refreshToken);
        Member member = memberRepository.findByMemberId(memberId)
                .orElseThrow(() -> new NoSuchElementException("MEMBER NOT FOUND"));

        // 가져온 member로 authorities 생성
        Set<GrantedAuthority> authorities = member.getAuthorities().stream()
                .map(SimpleGrantedAuthority::new).collect(Collectors.toSet());

        // 새로운 AccessToken을 위한 MemberDetails
        MemberDetails memberDetails = MemberDetails.builder()
                .username(member.getMemberId())
                .password(member.getMemberPassword())
                .careNo(member.getCareInfo().getCareNo())
                .authorities(authorities).build();

        // DB 정보로 authentication 생성
        Authentication authentication =
                new UsernamePasswordAuthenticationToken(memberDetails, null, authorities);

        log.info("authentication 생성 완료");
        // 인증 정보 기반으로 Token 생성
        return tokenProvider.refreshAccessToken(refreshToken, memberId, authentication);
    }

}
