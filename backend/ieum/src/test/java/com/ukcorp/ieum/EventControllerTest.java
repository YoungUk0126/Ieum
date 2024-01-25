package com.ukcorp.ieum;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Slf4j
@AutoConfigureMockMvc
public class EventControllerTest {

    @Autowired
    private MockMvc mock;


    @Test
    void build() {
        log.debug("Success Build");
    }


    @Test
    @DisplayName("기념일 정보 등록")
    void testInsert() throws Exception {

        String requestBody = "{\"care_no\": \"1\", \"event_name\": \"둘째 아들 생일\", \"event_date\": \"1998-01-26\"}";
//        post
        mock.perform(post("/api/event")
                        .content(requestBody)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect((content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))).andDo(print());
    }
    @Test
    @DisplayName("기념일 상세 정보 조회")
    void testSelect() throws Exception {

//        get(event-no)
        mock.perform(get("/api/event/detail/1"))
                .andExpect(status().isOk())
                .andExpect((content().contentType(MediaType.APPLICATION_JSON))).andDo(print());
    }

    @Test
    @DisplayName("기념일 정보 리스트 조회")
    void testSelectList() throws Exception {

//        get(care-no)
        mock.perform(get("/api/event/list/1"))
                .andExpect(status().isOk())
                .andExpect((content().contentType(MediaType.APPLICATION_JSON))).andDo(print());
    }

    @Test
    @DisplayName("기념일 정보 수정")
    void testUpdate() throws Exception {
        String requestBody = "{\"event_no\" : \"1\", \"care_no\": \"1\", \"event_name\": \"둘째 아들 생일\", \"event_date\": \"1998-01-26\"}";;

//        put
        mock.perform(put("/api/event")
                        .content(requestBody)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect((content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))).andDo(print());
    }

    @Test
    @DisplayName("기념일 정보 삭제")
    void testDelete() throws Exception {

//        delete
        mock.perform(delete("/api/care/1"))
                .andExpect(status().isOk())
                .andExpect((content().contentType(MediaType.APPLICATION_JSON))).andDo(print());
    }
}
