package com.ukcorp.ieum;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ukcorp.ieum.config.SecurityTestConfig;
import com.ukcorp.ieum.pill.controller.PillController;
import com.ukcorp.ieum.pill.dto.response.PillInfoJoinResponseDto;
import com.ukcorp.ieum.pill.service.PillServiceImpl;
import com.ukcorp.ieum.temporalEvent.controller.TemporalEventController;
import com.ukcorp.ieum.temporalEvent.dto.request.TemporalEventInsertRequestDto;
import com.ukcorp.ieum.temporalEvent.dto.request.TemporalEventUpdateRequestDto;
import com.ukcorp.ieum.temporalEvent.dto.response.TemporalEventResponseDto;
import com.ukcorp.ieum.temporalEvent.service.TemporalEventService;
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
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(TemporalEventController.class)
@ExtendWith(MockitoExtension.class)
@Import(SecurityTestConfig.class)
@Slf4j
class TemporalEventControllerTests {

    @Autowired
    private MockMvc mock;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private TemporalEventService temporalEventService;

    @Test
    void build() {
        log.debug("Success Build");
    }

    @Test
    @DisplayName("일정 등록")
    @WithMockUser(username = "test123", authorities = {"ROLE_USER"})
    void testRegist() throws Exception {
        // 일정 등록 관련 테스트
        TemporalEventInsertRequestDto requestDto = TemporalEventInsertRequestDto.builder().build();
        String requestBody = objectMapper.writeValueAsString(requestDto);

        doNothing().when(temporalEventService).registEvent(requestDto);

        mock.perform(post("/api/event")
                        .content(requestBody)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect((content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))).andDo(print());
    }

    @Test
    @DisplayName("일정 삭제")
    @WithMockUser(username = "test123", authorities = {"ROLE_USER"})
    void testDelete() throws Exception {
        doNothing().when(temporalEventService).deleteEvent(anyLong());

        // 17에 해당하는 eventNo 일정 삭제
        mock.perform(delete("/api/event/1"))
                .andExpect(status().isOk())
                .andExpect((content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))).andDo(print());
    }

    @Test
    @DisplayName("일정 리스트 조회")
    @WithMockUser(username = "test123", authorities = {"ROLE_USER"})
    void testSelect() throws Exception {
        // given
        List<TemporalEventResponseDto> response = new ArrayList<>();
        response.add(TemporalEventResponseDto.builder().build());

        Mockito.when(temporalEventService.getList()).thenReturn(response);

        // 사용자의 일정 리스트 조회
        mock.perform(get("/api/event"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data[0].eventNo").value(response.get(0).getEventNo()))
                .andExpect(jsonPath("$.data[0].careNo").value(response.get(0).getCareNo()))
                .andExpect(jsonPath("$.data[0].eventName").value(response.get(0).getEventName()))
                .andExpect(jsonPath("$.data[0].eventDate").value(response.get(0).getEventDate()))
                .andExpect((content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))).andDo(print());
    }

    @Test
    @DisplayName("일정 상세 조회")
    @WithMockUser(username = "test123", authorities = {"ROLE_USER"})
    void testSelectDetail() throws Exception {
        // given
        TemporalEventResponseDto response = TemporalEventResponseDto.builder().build();

        Mockito.when(temporalEventService.getDetail(anyLong())).thenReturn(response);

        // eventNo으로 해당 일정 상세 조회
        mock.perform(get("/api/event/detail/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data.eventNo").value(response.getEventNo()))
                .andExpect(jsonPath("$.data.careNo").value(response.getCareNo()))
                .andExpect(jsonPath("$.data.eventName").value(response.getEventName()))
                .andExpect(jsonPath("$.data.eventDate").value(response.getEventDate()))
                .andExpect((content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))).andDo(print());
    }

    @Test
    @DisplayName("일정 수정")
    @WithMockUser(username = "test123", authorities = {"ROLE_USER"})
    void testUpdate() throws Exception {
        TemporalEventUpdateRequestDto requestDto = TemporalEventUpdateRequestDto.builder().build();
        String requestBody = objectMapper.writeValueAsString(requestDto);

        doNothing().when(temporalEventService).modifyEvent(requestDto);

        mock.perform(put("/api/event")
                        .content(requestBody)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect((content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))).andDo(print());
    }

}