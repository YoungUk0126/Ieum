package com.ukcorp.ieum;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ukcorp.ieum.config.SecurityTestConfig;
import com.ukcorp.ieum.event.controller.EventController;
import com.ukcorp.ieum.event.dto.request.EventInsertRequestDto;
import com.ukcorp.ieum.event.dto.request.EventUpdateRequestDto;
import com.ukcorp.ieum.event.dto.response.EventGetResponseDto;
import com.ukcorp.ieum.event.service.EventServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(EventController.class)
@ExtendWith(MockitoExtension.class)
@Import(SecurityTestConfig.class)
@Slf4j
public class EventControllerTest {

    @Autowired
    private MockMvc mock;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private EventServiceImpl eventService;


    @Test
    void build() {
        log.debug("Success Build");
    }


    @Test
    @DisplayName("기념일 정보 등록")
    @WithMockUser(username = "test123", authorities = {"ROLE_USER"})
    void testInsert() throws Exception {
        EventInsertRequestDto requestDto = EventInsertRequestDto.builder().build();
        String requestBody = objectMapper.writeValueAsString(requestDto);

        doNothing().when(eventService).insertEvent(requestDto);

        // post
        mock.perform(post("/api/ani")
                        .content(requestBody)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect((content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))).andDo(print());
    }

    @Test
    @DisplayName("기념일 상세 정보 조회")
    @WithMockUser(username = "test123", authorities = {"ROLE_USER"})
    void testSelect() throws Exception {
        // given
        EventGetResponseDto response = EventGetResponseDto.builder().build();

        Mockito.when(eventService.getEvent(anyLong())).thenReturn(response);

        // get(event-no)
        mock.perform(get("/api/ani/detail/1"))
                .andExpect(status().isOk())
                .andExpect((content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON)))
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data.eventNo").value(response.getEventNo()))
                .andExpect(jsonPath("$.data.careNo").value(response.getCareNo()))
                .andExpect(jsonPath("$.data.eventName").value(response.getEventName()))
                .andExpect(jsonPath("$.data.eventDate").value(response.getEventDate()))
                .andExpect((content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))).andDo(print());
    }

    @Test
    @DisplayName("기념일 정보 리스트 조회")
    @WithMockUser(username = "test123", authorities = {"ROLE_USER"})
    void testSelectList() throws Exception {
        // given
        List<EventGetResponseDto> response = new ArrayList<>();
        response.add(EventGetResponseDto.builder().build());

        Mockito.when(eventService.getAllEvent()).thenReturn(response);

        // get(care-no)
        mock.perform(get("/api/ani"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data[0].eventNo").value(response.get(0).getEventNo()))
                .andExpect(jsonPath("$.data[0].careNo").value(response.get(0).getCareNo()))
                .andExpect(jsonPath("$.data[0].eventName").value(response.get(0).getEventName()))
                .andExpect(jsonPath("$.data[0].eventDate").value(response.get(0).getEventDate()))
                .andExpect((content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))).andDo(print());
    }

    @Test
    @DisplayName("기념일 정보 수정")
    @WithMockUser(username = "test123", authorities = {"ROLE_USER"})
    void testUpdate() throws Exception {
        EventUpdateRequestDto requestDto = EventUpdateRequestDto.builder()
                .eventNo(1L)
                .eventName("둘째 아들 생일")
                .eventDate(LocalDate.now()).build();

        String requestBody = objectMapper.writeValueAsString(requestDto);

        doNothing().when(eventService).updateEvent(requestDto);

        // put
        mock.perform(put("/api/ani")
                        .content(requestBody)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect((content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))).andDo(print());
    }

    @Test
    @DisplayName("기념일 정보 삭제")
    @WithMockUser(username = "test123", authorities = {"ROLE_USER"})
    void testDelete() throws Exception {
        doNothing().when(eventService).deleteEvent(anyLong());

        // delete
        mock.perform(delete("/api/ani/1"))
                .andExpect(status().isOk())
                .andExpect((content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))).andDo(print());
    }
}
