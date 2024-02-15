package com.ukcorp.ieum;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.ukcorp.ieum.config.SecurityTestConfig;
import com.ukcorp.ieum.event.controller.EventController;
import com.ukcorp.ieum.event.dto.response.EventGetResponseDto;
import com.ukcorp.ieum.event.service.EventServiceImpl;
import com.ukcorp.ieum.pill.controller.PillController;
import com.ukcorp.ieum.pill.dto.request.PillInfoInsertRequestDto;
import com.ukcorp.ieum.pill.dto.request.PillInfoUpdateRequestDto;
import com.ukcorp.ieum.pill.dto.request.PillTimeInsertRequestDto;
import com.ukcorp.ieum.pill.dto.request.PillTimeUpdateRequestDto;
import com.ukcorp.ieum.pill.dto.response.PillInfoJoinResponseDto;
import com.ukcorp.ieum.pill.dto.response.PillTimeGetResponseDto;
import com.ukcorp.ieum.pill.service.PillServiceImpl;
import com.ukcorp.ieum.socket.dto.response.PillResponseDto;
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

@WebMvcTest(PillController.class)
@ExtendWith(MockitoExtension.class)
@Import(SecurityTestConfig.class)
@Slf4j
public class PillControllerTest {

    @Autowired
    private MockMvc mock;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private PillServiceImpl pillService;

    @Test
    void build() {
        log.debug("Success Build");
    }

    @Test
    @DisplayName("약 정보 등록")
    @WithMockUser(username = "test123", authorities = {"ROLE_USER"})
    void testRegist() throws Exception {
        // 약 정보 등록 관련 테스트
        PillInfoInsertRequestDto requestDto = PillInfoInsertRequestDto.builder().build();
        List<PillTimeInsertRequestDto> pillTimes = new ArrayList<>();
        requestDto.setPillTimes(pillTimes);

        // JSON 변환
        String requestBody = objectMapper.writeValueAsString(requestDto);

        doNothing().when(pillService).insertPill(requestDto);

        mock.perform(post("/api/pill")
                        .content(requestBody)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect((content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))).andDo(print());
    }

    @Test
    @DisplayName("약 정보 삭제")
    @WithMockUser(username = "test123", authorities = {"ROLE_USER"})
    void testDelete() throws Exception {
        doNothing().when(pillService).deletePillInfo(anyLong());

        // 1에 해당하는 pillInfo 삭제
        mock.perform(delete("/api/pill/2"))
                .andExpect(status().isOk())
                .andExpect((content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))).andDo(print());
    }

    @Test
    @DisplayName("약 복용 시간 정보 삭제")
    @WithMockUser(username = "test123", authorities = {"ROLE_USER"})
    void testTimeDelete() throws Exception {
        doNothing().when(pillService).deletePillTime(anyLong());

        // 1에 해당하는 pillTime 삭제
        mock.perform(delete("/api/pill/time/4"))
                .andExpect(status().isOk())
                .andExpect((content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))).andDo(print());
    }


    @Test
    @DisplayName("약 정보 리스트 조회")
    @WithMockUser(username = "test123", authorities = {"ROLE_USER"})
    void testSelectList() throws Exception {
        // given
        List<PillInfoJoinResponseDto> response = new ArrayList<>();
        response.add(PillInfoJoinResponseDto.builder().build());

        Mockito.when(pillService.getAllPillInfo()).thenReturn(response);

        // careNo가 1인 사용자의 일정 리스트 조회
        mock.perform(get("/api/pill"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data[0].pillInfoNo").value(response.get(0).getPillInfoNo()))
                .andExpect(jsonPath("$.data[0].pillName").value(response.get(0).getPillName()))
                .andExpect(jsonPath("$.data[0].pillStartDate").value(response.get(0).getPillStartDate()))
                .andExpect(jsonPath("$.data[0].pillEndDate").value(response.get(0).getPillEndDate()))
                .andExpect(jsonPath("$.data[0].pillMethod").value(response.get(0).getPillMethod()))
                .andExpect(jsonPath("$.data[0].pillDate").value(response.get(0).getPillDate()))
                .andExpect(jsonPath("$.data[0].pillTimes").value(response.get(0).getPillTimes()))
                .andExpect((content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))).andDo(print());
    }

    @Test
    @DisplayName("약 정보 상세 조회")
    @WithMockUser(username = "test123", authorities = {"ROLE_USER"})
    void testSelectDetail() throws Exception {
        // given
        PillInfoJoinResponseDto response = PillInfoJoinResponseDto.builder().build();

        Mockito.when(pillService.getPillInfo(anyLong())).thenReturn(response);

        // eventNo으로 해당 일정 상세 조회
        mock.perform(get("/api/pill/detail/3"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data.pillInfoNo").value(response.getPillInfoNo()))
                .andExpect(jsonPath("$.data.pillName").value(response.getPillName()))
                .andExpect(jsonPath("$.data.pillStartDate").value(response.getPillStartDate()))
                .andExpect(jsonPath("$.data.pillEndDate").value(response.getPillEndDate()))
                .andExpect(jsonPath("$.data.pillMethod").value(response.getPillMethod()))
                .andExpect(jsonPath("$.data.pillDate").value(response.getPillDate()))
                .andExpect(jsonPath("$.data.pillTimes").value(response.getPillTimes()))
                .andExpect((content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))).andDo(print());
    }


    @Test
    @DisplayName("일정 수정")
    @WithMockUser(username = "test123", authorities = {"ROLE_USER"})
    void testUpdate() throws Exception {

        // 약 정보 등록 관련 테스트
        PillInfoUpdateRequestDto requestDto = new PillInfoUpdateRequestDto();
        List<PillTimeUpdateRequestDto> pillTimes = new ArrayList<>();
        requestDto.setPillTimes(pillTimes);

        // JSON 변환
        String requestBody = objectMapper.writeValueAsString(requestDto);

        doNothing().when(pillService).updatePill(requestDto);

        mock.perform(put("/api/pill")
                        .content(requestBody)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect((content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))).andDo(print());
    }
}