package com.ukcorp.ieum.member.service;

import com.ukcorp.ieum.Sms.ValidationUtil;
import com.ukcorp.ieum.api.service.NaverService;
import com.ukcorp.ieum.care.entity.CareInfo;
import com.ukcorp.ieum.care.repository.CareRepository;
import com.ukcorp.ieum.jwt.JwtUtil;
import com.ukcorp.ieum.jwt.TokenProvider;
import com.ukcorp.ieum.jwt.dto.JwtToken;
import com.ukcorp.ieum.member.dto.MemberIdPasswordDto;
import com.ukcorp.ieum.member.dto.MemberRequestDto;
import com.ukcorp.ieum.member.dto.MemberResponseDto;
import com.ukcorp.ieum.member.dto.MemberUpdateDto;
import com.ukcorp.ieum.member.entity.Member;
import com.ukcorp.ieum.member.mapper.MemberMapper;
import com.ukcorp.ieum.member.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@WebMvcTest(MemberService.class)
@ExtendWith(MockitoExtension.class)
@Slf4j
class MemberServiceTest {

    @Autowired
    private MemberService memberService;

    @MockBean
    private MemberRepository memberRepository;

    @MockBean
    private MemberMapper memberMapper;

    @MockBean
    private CareRepository careRepository;

    @MockBean
    private NaverService naverService;

    @MockBean
    private ValidationUtil validationUtil;

    @MockBean
    private AuthenticationManagerBuilder authenticationManagerBuilder;

    @MockBean
    private TokenProvider tokenProvider;

    @MockBean
    private PasswordEncoder passwordEncoder;

    @Mock
    private JwtUtil jwtUtil;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void 회원가입() throws Exception {
        // given
        MemberRequestDto memberSignupDto = createMemberRequestDto();
        Member newMember = Member.builder().build();
        CareInfo careInfo = CareInfo.builder().careNo(1L).build();

        when(memberMapper.memberRequestDtoToMember(memberSignupDto)).thenReturn(newMember);
        when(careRepository.save(any(CareInfo.class))).thenReturn(careInfo);

        // when
        memberService.signup(memberSignupDto);

        // then
        verify(memberMapper).memberRequestDtoToMember(memberSignupDto);
        verify(careRepository).save(any(CareInfo.class));
        verify(memberRepository).save(newMember);
    }

    // TODO : 로그인 테스트

    @Test
    void 로그아웃() {
        // Given
        String memberId = "test123";
        // Mock Authentication 객체 생성
        Authentication authenticationMock = mock(Authentication.class);
        UserDetails userDetailsMock = mock(UserDetails.class);
        when(userDetailsMock.getUsername()).thenReturn(memberId);
        when(authenticationMock.getPrincipal()).thenReturn(userDetailsMock);
        SecurityContextHolder.getContext().setAuthentication(authenticationMock);

        // when
        memberService.logout();

        // Then
        verify(tokenProvider).deleteRefreshTokenFromRedis(memberId);
    }

    @Test
    void 멤버정보조회() {
        // given
//        when()
    }

    @Test
    void modifyMember() {
    }

    @Test
    void modifyMemberPassword() {
    }

    @Test
    void withdrawMember() {
    }

    @Test
    void isExistsMemberId() {
    }

    @Test
    void isExistsMemberEmail() {
    }

    @Test
    void isExistsMemberPhone() {
    }

    @Test
    void checkExistsMember() {
    }

    MemberRequestDto createMemberRequestDto() {
        return MemberRequestDto.builder()
                .memberId("test123")
                .password("password123!")
                .name("테스트")
                .email("email!@gmail.com")
                .phone("010-1234-5678").build();
    }

    MemberResponseDto createMemberResponseDto() {
        return MemberResponseDto.builder()
                .memberId("test123")
                .name("테스트")
                .email("test@gmail.com")
                .phone("010-1234-5678").build();
    }

    MemberUpdateDto createUpdateDto(String password) {
        return MemberUpdateDto.builder()
                .memberId("test123")
                .name("수정됨")
                .password(password)
                .phone("010-9876-5432")
                .email("modify@gmail.com").build();
    }

    MemberIdPasswordDto createMemberIdPasswordDto() {
        return MemberIdPasswordDto.builder()
                .memberId("test123")
                .password("password123!").build();
    }
}