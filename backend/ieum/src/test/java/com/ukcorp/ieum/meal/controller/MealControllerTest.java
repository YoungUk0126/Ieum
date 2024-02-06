package com.ukcorp.ieum.meal.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.ukcorp.ieum.config.SecurityTestConfig;
import com.ukcorp.ieum.meal.dto.request.MealInsertRequestDto;
import com.ukcorp.ieum.meal.dto.request.MealUpdateRequestDto;
import com.ukcorp.ieum.meal.dto.response.MealGetResponseDto;
import com.ukcorp.ieum.meal.service.MealServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
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
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(MealController.class)
@ExtendWith(MockitoExtension.class)
@Import(SecurityTestConfig.class)
@Slf4j
public class MealControllerTest {

    @Autowired
    private MockMvc mock;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private MealServiceImpl mealService;

    @Test
    void build() {
        log.debug("Success Build");
    }

    @Test
    @DisplayName("약 정보 등록")
    @WithMockUser(username = "test123", authorities = {"ROLE_USER"})
    void testInsert() throws Exception {
        MealInsertRequestDto requestDto = MealInsertRequestDto.builder()
                .mealTime1(70000L)
                .mealTime2(120000L)
                .mealTime3(190000L)
                .build();

        //    // JSON 변환
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        String requestBody = objectMapper.writeValueAsString(requestDto);

        doNothing().when(mealService).insertMeal(any(MealInsertRequestDto.class));

        mock.perform(post("/api/meal")
                        .content(requestBody)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect((content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON)))
                .andDo(print());
    }

    @Test
    @DisplayName("약 정보 조회")
    @WithMockUser(username = "test123", authorities = {"ROLE_USER"})
    void testSelect() throws Exception {
        // given
        MealGetResponseDto response = MealGetResponseDto.builder().build();

        when(mealService.getMeal()).thenReturn(response);

        // get
        mock.perform(get("/api/meal"))
                .andExpect(status().isOk())
                .andExpect((content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON)))
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data.mealInfoNo").value(response.getMealInfoNo()))
                .andExpect(jsonPath("$.data.careNo").value(response.getCareNo()))
                .andExpect(jsonPath("$.data.mealTime1").value(response.getMealTime1()))
                .andExpect(jsonPath("$.data.mealTime2").value(response.getMealTime2()))
                .andExpect(jsonPath("$.data.mealTime3").value(response.getMealTime3()))
                .andDo(print());
    }

    @Test
    @DisplayName("약 정보 수정")
    @WithMockUser(username = "test123", authorities = {"ROLE_USER"})
    void testUpdate() throws Exception {
        MealUpdateRequestDto requestDto = MealUpdateRequestDto.builder()
                .mealInfoNo(2L)
                .mealTime1(75000L)
                .mealTime2(125000L)
                .mealTime3(195000L)
                .build();

        //    // JSON 변환
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        String requestBody = objectMapper.writeValueAsString(requestDto);

        doNothing().when(mealService).updateMeal(any(MealUpdateRequestDto.class));

        mock.perform(put("/api/meal")
                        .content(requestBody)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect((content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON)))
                .andDo(print());
    }

    @Test
    @DisplayName("약 정보 삭제")
    @WithMockUser(username = "test123", authorities = {"ROLE_USER"})
    void testDelete() throws Exception {
        doNothing().when(mealService).deleteMeal(anyLong());

//        delete
        mock.perform(delete("/api/meal/1"))
                .andExpect(status().isOk())
                .andExpect((content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))).andDo(print());
    }
}

