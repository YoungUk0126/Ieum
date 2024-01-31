package com.ukcorp.ieum.member.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.ukcorp.ieum.member.dto.MemberLoginRequestDto;
import com.ukcorp.ieum.member.dto.MemberRequestDto;
import com.ukcorp.ieum.member.service.MemberService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Slf4j
@AutoConfigureMockMvc
@Transactional
class MemberControllerTest {

    @Autowired
    private MockMvc mockMvc;
    private ObjectMapper objectMapper = new ObjectMapper()
            .setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);

    @Test
    void 회원가입_성공() throws Exception {
        MemberRequestDto signupTestMember = MemberRequestDto.builder()
                .memberId("test123")
                .password("password123!!")
                .name("테스트")
                .email("test@naver.com")
                .phone("010-1234-5678").build();

        String requestBody = objectMapper.writeValueAsString(signupTestMember);

        mockMvc.perform(post("/api/member/join")
                        .content(requestBody)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void 로그인_성공() throws Exception {
        MemberLoginRequestDto loginMember = MemberLoginRequestDto.builder()
                .memberId("test123")
                .password("password123!!").build();

        mockMvc.perform(post("/api/member/login")
                .content(objectMapper.writeValueAsString(loginMember))
                .contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    @WithMockUser(username = "newjoin", authorities = {"ROLE_USER"})
    void 회원정보_업데이트() throws Exception {
        MemberRequestDto updateMember = MemberRequestDto.builder()
                .memberId("newjoin")
                .password("change123!")
                .name("변경")
                .phone("010-1236-2394")
                .email("change123@gmail.com").build();

        mockMvc.perform(put("/api/member")
                .content(objectMapper.writeValueAsString(updateMember))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void 아이디중복확인() throws Exception {
        String checkId = "test123";

        mockMvc.perform(get("/api/member/check-id/"+checkId))
                .andExpect(status().isOk());
    }

    @Test
    void 이메일중복확인() throws Exception {
        String requestBody = "{\"email\": \"test123@gmail.com\"}";

        mockMvc.perform(post("/api/member/check-email")
                .content(requestBody)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void 핸드폰중복확인() throws Exception {
        String requestBody = "{\"phone\": \"010-1234-5234\"}";

        mockMvc.perform(post("/api/member/check-phone")
                .content(requestBody)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}