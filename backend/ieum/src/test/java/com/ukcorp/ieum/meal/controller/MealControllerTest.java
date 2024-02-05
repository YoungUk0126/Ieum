package com.ukcorp.ieum.meal.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.ukcorp.ieum.meal.dto.request.MealInsertRequestDto;
import com.ukcorp.ieum.meal.dto.request.MealUpdateRequestDto;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Slf4j
@AutoConfigureMockMvc
public class MealControllerTest {

    @Autowired
    private MockMvc mock;


    @Test
    void build() {
        log.debug("Success Build");
    }

    @Test
    @DisplayName("약 정보 등록")
    void testInsert() throws Exception {
        MealInsertRequestDto requestDto = MealInsertRequestDto.builder()
                .mealTime1(70000L)
                .mealTime2(120000L)
                .mealTime3(190000L)
                .build();

        //    // JSON 변환
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        String requestBody = objectMapper.writeValueAsString(requestDto);


//        String requestBody = "{\"care_no\": \"1\", \"meal_time1\": \"90000\", \"meal_time2\": \"140000\", \"meal_time3\": \"210000\"}";

        mock.perform(post("/api/meal")
                        .content(requestBody)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect((content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))).andDo(print());
    }

    @Test
    @DisplayName("약 정보 조회")
    void testSelect() throws Exception {
//        Query Param 담는애(get)
//        MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
//        Query Param 변수 이름
//        map.add("care-no", "1");

//        get
        mock.perform(get("/api/meal/1"))
                .andExpect(status().isOk())
                .andExpect((content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))).andDo(print());
    }

    @Test
    @DisplayName("약 정보 수정")
    void testUpdate() throws Exception {
        MealUpdateRequestDto requestDto = MealUpdateRequestDto.builder()
                .mealInfoNo(2L)
                .mealTime1(75000L)
                .mealTime2(125000L)
                .mealTime3(195000L)
                .build();

        //    // JSON 변환
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        String requestBody = objectMapper.writeValueAsString(requestDto);

//        String requestBody = "{\"care_no\": \"1\", \"meal_info_no\": \"1\", \"meal_time1\": \"90000\", \"meal_time2\": \"130000\", \"meal_time3\": \"200000\"}";

        mock.perform(put("/api/meal")
                        .content(requestBody)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect((content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))).andDo(print());

    }

    @Test
    @DisplayName("약 정보 삭제")
    void testDelete() throws Exception {
//        delete
        mock.perform(delete("/api/meal/1"))
                .andExpect(status().isOk())
                .andExpect((content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))).andDo(print());
    }
}

