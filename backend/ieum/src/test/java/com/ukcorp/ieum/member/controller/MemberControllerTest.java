package com.ukcorp.ieum.member.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.ukcorp.ieum.member.dto.MemberLoginRequestDto;
import com.ukcorp.ieum.member.dto.MemberRequestDto;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Slf4j
@AutoConfigureMockMvc
class MemberControllerTest {

    @Autowired
    private MockMvc mockMvc;
    private ObjectMapper objectMapper = new ObjectMapper()
            .setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);

    @Test
    void 회원가입_성공() throws Exception {
        MemberRequestDto signupTestMember = MemberRequestDto.builder()
                .memberId("test123")
                .password("password!!")
                .name("테스트")
                .email("test@naver.com")
                .phone("010-1234-5678").build();

        String requestBody = objectMapper.writeValueAsString(signupTestMember);
        log.info(requestBody);

        mockMvc.perform(post("/api/member/join")
                        .content(requestBody)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void 로그인_성공() throws Exception {
        // given
        MemberLoginRequestDto loginMember = MemberLoginRequestDto.builder()
                .memberId("test123")
                .password("password!!").build();

        // when
        mockMvc.perform(post("/api/member/login")
                .content(objectMapper.writeValueAsString(loginMember))
                .contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void 아이디중복확인() throws Exception {
        // given
        String checkId = "test123";

        mockMvc.perform(get("/api/member/check-id/"+checkId))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "test123", authorities = {"ROLE_USER"})
    void 이메일중복확인() throws Exception {
        String requestBody = "{\"email\": \"test123@gmail.com\"}";
        log.info(requestBody);

        mockMvc.perform(post("/api/member/check-email")
                .content(requestBody)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "test123", authorities = {"ROLE_USER"})
    void 핸드폰중복확인() throws Exception {
        String requestBody = "{\"phone\": \"010-1234-5234\"}";

//        String requestBody = objectMapper.writeValueAsString(phone);
        log.info(requestBody);
        mockMvc.perform(post("/api/member/check-phone")
                .content(requestBody)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}