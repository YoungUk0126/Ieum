package com.ukcorp.ieum;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ukcorp.ieum.care.dto.request.CareUpdateRequestDto;
import com.ukcorp.ieum.config.SecurityTestConfig;
import com.ukcorp.ieum.event.controller.EventController;
import com.ukcorp.ieum.event.dto.response.EventGetResponseDto;
import com.ukcorp.ieum.event.service.EventServiceImpl;
import com.ukcorp.ieum.message.controller.MessageController;
import com.ukcorp.ieum.message.dto.request.MessageInsertRequestDto;
import com.ukcorp.ieum.message.dto.request.MessageUpdateRequestDto;
import com.ukcorp.ieum.message.dto.response.MessageResponseDto;
import com.ukcorp.ieum.message.service.MessageService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.mock.web.MockPart;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static reactor.core.publisher.Mono.when;

@WebMvcTest(MessageController.class)
@ExtendWith(MockitoExtension.class)
@Import(SecurityTestConfig.class)
@Slf4j
class MessageControllerTests {

    @Autowired
    private MockMvc mock;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private MessageService messageService;

    @Test
    void build() {
        log.debug("Success Build");
    }

    @Test
    @DisplayName("메세지 등록")
    @WithMockUser(username = "test123", authorities = {"ROLE_USER"})
    void testRegist() throws Exception {
        // 메세지 등록 관련 테스트
        MessageInsertRequestDto requestDto = MessageInsertRequestDto.builder().build();
        String requestBody = objectMapper.writeValueAsString(requestDto);
        MockMultipartFile dto = new MockMultipartFile("data", "message",
                "application/json", requestBody.getBytes(StandardCharsets.UTF_8));

        MockMultipartFile image = new MockMultipartFile("file",
                "test.jpg", "text/plain", "test file".getBytes(StandardCharsets.UTF_8));

        doNothing().when(messageService).registMessage(any(MessageInsertRequestDto.class));

        mock.perform(multipart("/api/message")
                        .file(dto)
                        .file(image)
                        .part(new MockPart("data", requestBody.getBytes(StandardCharsets.UTF_8)))
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("메세지 삭제")
    @WithMockUser(username = "test123", authorities = {"ROLE_USER"})
    void testDelete() throws Exception {
        doNothing().when(messageService).deleteMessage(anyLong());

        // 1에 해당하는 messageNo 메세지 삭제
        mock.perform(delete("/api/message/1"))
                .andExpect(status().isOk())
                .andExpect((content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))).andDo(print());
    }


    @Test
    @DisplayName("메세지 리스트 조회")
    @WithMockUser(username = "test123", authorities = {"ROLE_USER"})
    void testSelect() throws Exception {
        // given
        List<MessageResponseDto> response = new ArrayList<>();
        response.add(MessageResponseDto.builder().build());

        Mockito.when(messageService.getList()).thenReturn(response);

        // messageNo으로 해당 메세지 상세 조회
        mock.perform(get("/api/message"))
                .andExpect(status().isOk())
                .andExpect((content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON)))
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data[0].messageNo").value(response.get(0).getMessageNo()))
                .andExpect(jsonPath("$.data[0].careNo").value(response.get(0).getCareNo()))
                .andExpect(jsonPath("$.data[0].messageSender").value(response.get(0).getMessageSender()))
                .andExpect(jsonPath("$.data[0].messageName").value(response.get(0).getMessageName()))
                .andExpect(jsonPath("$.data[0].messageType").value(response.get(0).getMessageType()))
                .andExpect(jsonPath("$.data[0].messageTime").value(response.get(0).getMessageTime()))
                .andExpect((content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))).andDo(print());


        // careNo이 1인 사용자의 메세지 리스트 조회
        mock.perform(get("/api/message"))
                .andExpect(status().isOk())
                .andExpect((content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))).andDo(print());
    }

    @Test
    @DisplayName("메세지 상세 조회")
    @WithMockUser(username = "test123", authorities = {"ROLE_USER"})
    void testSelectDetail() throws Exception {
        // given
        MessageResponseDto response = MessageResponseDto.builder().build();

        Mockito.when(messageService.getDetail(anyLong())).thenReturn(response);

        // messageNo으로 해당 메세지 상세 조회
        mock.perform(get("/api/message/detail/1"))
                .andExpect(status().isOk())
                .andExpect((content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON)))
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data.messageNo").value(response.getMessageNo()))
                .andExpect(jsonPath("$.data.careNo").value(response.getCareNo()))
                .andExpect(jsonPath("$.data.messageSender").value(response.getMessageSender()))
                .andExpect(jsonPath("$.data.messageName").value(response.getMessageName()))
                .andExpect(jsonPath("$.data.messageType").value(response.getMessageType()))
                .andExpect(jsonPath("$.data.messageTime").value(response.getMessageTime()))
                .andExpect((content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))).andDo(print());
    }

    @Test
    @DisplayName("메세지 수정")
    @WithMockUser(username = "test123", authorities = {"ROLE_USER"})
    void testUpdate() throws Exception {
        MessageUpdateRequestDto requestDto = MessageUpdateRequestDto.builder().build();
        String requestBody = objectMapper.writeValueAsString(requestDto);
        MockMultipartFile dto = new MockMultipartFile("data", "message",
                "application/json", requestBody.getBytes(StandardCharsets.UTF_8));

        MockMultipartFile image = new MockMultipartFile("file",
                "test.jpg", "text/plain", "test file".getBytes(StandardCharsets.UTF_8));

        doNothing().when(messageService).modifyMessage(any(MessageUpdateRequestDto.class));

        mock.perform(multipart(HttpMethod.PUT, "/api/message")
                        .file(dto)
                        .file(image)
                        .part(new MockPart("data", requestBody.getBytes(StandardCharsets.UTF_8)))
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk());
    }

}