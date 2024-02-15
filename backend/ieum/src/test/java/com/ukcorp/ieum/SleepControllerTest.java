package com.ukcorp.ieum;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ukcorp.ieum.config.SecurityTestConfig;
import com.ukcorp.ieum.event.controller.EventController;
import com.ukcorp.ieum.event.service.EventServiceImpl;
import com.ukcorp.ieum.sleep.controller.SleepController;
import com.ukcorp.ieum.sleep.dto.request.SleepInsertRequestDto;
import com.ukcorp.ieum.sleep.dto.request.SleepUpdateRequestDto;
import com.ukcorp.ieum.sleep.dto.response.SleepResponseDto;
import com.ukcorp.ieum.sleep.service.SleepService;
import com.ukcorp.ieum.sleep.service.SleepServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
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

import java.time.LocalTime;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(SleepController.class)
@ExtendWith(MockitoExtension.class)
@Import(SecurityTestConfig.class)
@Slf4j
public class SleepControllerTest {
    @Autowired
    private MockMvc mock;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private SleepService sleepService;


    @Test
    void build() {
        log.debug("Success Build");
    }

    @Test
    @DisplayName("방해금지모드 정보 등록")
    @WithMockUser(username = "test123", authorities = {"ROLE_USER"})
    void testInsert() throws Exception {
        SleepInsertRequestDto requestDto = SleepInsertRequestDto.builder().build();
        String requestBody = objectMapper.writeValueAsString(requestDto);

        doNothing().when(sleepService).registSleep(requestDto);

        // post
        mock.perform(post("/api/sleepMode")
                        .content(requestBody)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect((content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))).andDo(print());
    }

    @Test
    @DisplayName("방해금지모드 정보 조회")
    @WithMockUser(username = "test123", authorities = {"ROLE_USER"})
    void testSelect() throws Exception {
        SleepResponseDto response = SleepResponseDto.builder().build();

        when(sleepService.getSleep()).thenReturn(response);

        // get (event-no)
        mock.perform(get("/api/sleepMode"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data.sleepInfoNo").value(response.getSleepInfoNo()))
                .andExpect(jsonPath("$.data.careNo").value(response.getCareNo()))
                .andExpect(jsonPath("$.data.sleepStartTime").value(response.getSleepStartTime()))
                .andExpect(jsonPath("$.data.sleepEndTime").value(response.getSleepEndTime()))
                .andExpect((content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))).andDo(print());
    }


    @Test
    @DisplayName("방해금지모드 정보 수정")
    @WithMockUser(username = "test123", authorities = {"ROLE_USER"})
    void testUpdate() throws Exception {
        SleepUpdateRequestDto requestDto = SleepUpdateRequestDto.builder().build();
        String requestBody = objectMapper.writeValueAsString(requestDto);

        doNothing().when(sleepService).modifySleep(requestDto);

        // put
        mock.perform(put("/api/sleepMode")
                        .content(requestBody)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect((content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))).andDo(print());
    }

    @Test
    @DisplayName("방해금지모드 정보 삭제")
    @WithMockUser(username = "test123", authorities = {"ROLE_USER"})
    void testDelete() throws Exception {
        doNothing().when(sleepService).deleteSleep(anyLong());

        // delete
        mock.perform(delete("/api/sleepMode/1"))
                .andExpect(status().isOk())
                .andExpect((content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))).andDo(print());
    }
}
