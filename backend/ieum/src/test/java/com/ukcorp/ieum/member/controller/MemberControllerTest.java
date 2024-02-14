package com.ukcorp.ieum.member.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ukcorp.ieum.config.SecurityTestConfig;
import com.ukcorp.ieum.jwt.dto.JwtToken;
import com.ukcorp.ieum.member.dto.MemberIdPasswordDto;
import com.ukcorp.ieum.member.dto.MemberRequestDto;
import com.ukcorp.ieum.member.dto.MemberResponseDto;
import com.ukcorp.ieum.member.dto.MemberUpdateDto;
import com.ukcorp.ieum.member.service.MemberServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(MemberController.class)
@ExtendWith(MockitoExtension.class)
@Import(SecurityTestConfig.class)
@Slf4j
class MemberControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private MemberServiceImpl memberService;

    @Test
    void 회원가입_성공() throws Exception {
        // given
        MemberRequestDto joinMember = createMemberRequestDto();

        // when
        doNothing().when(memberService).signup(any(MemberRequestDto.class));
        String requestBody = objectMapper.writeValueAsString(joinMember);

        // then
        mockMvc.perform(post("/api/member/join")
                        .content(requestBody)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andDo(print());
    }

    @Test
    void 로그인_성공() throws Exception {
        // given
        MemberIdPasswordDto loginMember = createMemberIdPasswordDto();
        JwtToken jwtToken = JwtToken.builder()
                .grantType("Bearer")
                .accessToken("accessTokenValue")
                .refreshToken("refreshTokenValue").build();
        String response = objectMapper.writeValueAsString(jwtToken);

        // when
        when(memberService.login(any(MemberIdPasswordDto.class))).thenReturn(jwtToken);

        // then
        mockMvc.perform(post("/api/member/login")
                        .content(objectMapper.writeValueAsString(loginMember))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(response))
                .andDo(print());
    }

    @Test
    @WithMockUser(username = "test123", authorities = {"ROLE_USER"})
    void 회원정보_조회() throws Exception {
        // given
        MemberResponseDto response = createMemberResponseDto();

        // when
        when(memberService.getMemberInfo()).thenReturn(response);

        // then
        mockMvc.perform(get("/api/member"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data.memberId").value(response.getMemberId()))
                .andExpect(jsonPath("$.data.name").value(response.getName()))
                .andExpect(jsonPath("$.data.phone").value(response.getPhone()))
                .andExpect(jsonPath("$.data.email").value(response.getEmail()))
                .andDo(print());
    }

    @Test
    @WithMockUser(username = "test123", authorities = {"ROLE_USER"})
    void 회원정보_업데이트_비밀번호포함() throws Exception {
        // given
        MemberUpdateDto updateMember = createUpdateDto("modify123!");

        // when
        doNothing().when(memberService).modifyMember(any(MemberUpdateDto.class));

        // then
        mockMvc.perform(put("/api/member")
                        .content(objectMapper.writeValueAsString(updateMember))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andDo(print());
    }

    @Test
    @WithMockUser(username = "test123", authorities = {"ROLE_USER"})
    void 회원정보_업데이트_비밀번호불포함() throws Exception {
        // given
        MemberUpdateDto updateMember = createUpdateDto(null);

        // when
        doNothing().when(memberService).modifyMember(any(MemberUpdateDto.class));

        // then
        mockMvc.perform(put("/api/member")
                        .content(objectMapper.writeValueAsString(updateMember))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andDo(print());
    }

    @Test
    @WithMockUser(username = "test123", authorities = {"ROLE_USER"})
    void 아이디중복확인_중복() throws Exception {
        // given
        String checkId = "test123";

        // when
        when(memberService.isExistsMemberId(anyString())).thenReturn(true);

        // then
        mockMvc.perform(get("/api/member/check-id/" + checkId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data.isDuplicated").value(true))
                .andDo(print());
    }

    @Test
    @WithMockUser(username = "test123", authorities = {"ROLE_USER"})
    void 아이디중복확인_중복아님() throws Exception {
        // given
        String checkId = "test123";

        // when
        when(memberService.isExistsMemberId(anyString())).thenReturn(false);

        // then
        mockMvc.perform(get("/api/member/check-id/" + checkId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data.isDuplicated").value(false))
                .andDo(print());
    }

    @Test
    @WithMockUser(username = "test123", authorities = {"ROLE_USER"})
    void 이메일중복확인_중복() throws Exception {
        // given
        String requestBody = "{\"email\": \"test123@gmail.com\"}";

        // when
        when(memberService.isExistsMemberEmail(anyString())).thenReturn(true);

        // then
        mockMvc.perform(post("/api/member/check-email")
                        .content(requestBody)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data.isDuplicated").value(true))
                .andDo(print());
    }

    @Test
    @WithMockUser(username = "test123", authorities = {"ROLE_USER"})
    void 이메일중복확인_중복아님() throws Exception {
        // given
        String requestBody = "{\"email\": \"test123@gmail.com\"}";

        // when
        when(memberService.isExistsMemberEmail(anyString())).thenReturn(false);

        // then
        mockMvc.perform(post("/api/member/check-email")
                        .content(requestBody)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data.isDuplicated").value(false))
                .andDo(print());
    }

    @Test
    @WithMockUser(username = "test123", authorities = {"ROLE_USER"})
    void 핸드폰중복확인_중복() throws Exception {
        // given
        String requestBody = "{\"phone\": \"010-1234-5234\"}";

        // when
        when(memberService.isExistsMemberPhone(anyString())).thenReturn(true);

        // then
        mockMvc.perform(post("/api/member/check-phone")
                        .content(requestBody)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data.isDuplicated").value(true))
                .andDo(print());
    }

    @Test
    @WithMockUser(username = "test123", authorities = {"ROLE_USER"})
    void 핸드폰중복확인_중복아님() throws Exception {
        // given
        String requestBody = "{\"phone\": \"010-1234-5234\"}";

        // when
        when(memberService.isExistsMemberPhone(anyString())).thenReturn(false);

        // then
        mockMvc.perform(post("/api/member/check-phone")
                        .content(requestBody)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data.isDuplicated").value(false))
                .andDo(print());
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