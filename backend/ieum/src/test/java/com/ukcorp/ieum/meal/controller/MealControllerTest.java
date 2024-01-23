package com.ukcorp.ieum.meal.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.ukcorp.ieum.meal.entity.Meal;
import com.ukcorp.ieum.meal.service.MealService;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.Optional;

@SpringBootTest
@Slf4j
@AutoConfigureMockMvc
public class MealControllerTest {

    @Autowired
    private MockMvc mock;

    @MockBean
    MealService service;

    @Test
    void build() {
        log.debug("Success Build");
    }

    @Test
    @DisplayName("약 정보 등록")
    void testInsert() throws Exception {
        String requestBody = "{\"meal_time1\": \"09:00:00\", \"meal_time2\": \"14:00:00\", \"meal_time3\": \"21:00:00\"}";

        mock.perform(post("/api/meal")
                        .content(requestBody)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect((content().contentType(MediaType.APPLICATION_JSON))).andDo(print());
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
                .andExpect((content().contentType(MediaType.APPLICATION_JSON))).andDo(print());
    }
}
