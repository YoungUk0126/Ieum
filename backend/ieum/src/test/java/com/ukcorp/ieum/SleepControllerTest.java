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
public class SleepControllerTest {
    @Autowired
    private MockMvc mock;


    @Test
    void build() {
        log.debug("Success Build");
    }

    @Test
    @DisplayName("방해금지모드 정보 등록")
    void testInsert() throws Exception {

        String requestBody = "{\"care_no\": \"1\", \"sleep_start_time\": \"21:30:00\", \"sleep_end_time\": \"06:00:00\"}";
//        post
        mock.perform(post("/api/sleepMode")
                        .content(requestBody)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect((content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))).andDo(print());
    }
    @Test
    @DisplayName("방해금지모드 정보 조회")
    void testSelect() throws Exception {

//        get(event-no)
        mock.perform(get("/api/sleepMode/1"))
                .andExpect(status().isOk())
                .andExpect((content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))).andDo(print());
    }


    @Test
    @DisplayName("방해금지모드 정보 수정")
    void testUpdate() throws Exception {
        String requestBody = "{\"sleep_info_no\" : \"1\", \"care_no\": \"1\", \"sleep_start_time\": \"22:00:00\", \"sleep_end_time\": \"07:00:00\"}";;

//        put
        mock.perform(put("/api/sleepMode")
                        .content(requestBody)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect((content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))).andDo(print());
    }

    @Test
    @DisplayName("방해금지모드 정보 삭제")
    void testDelete() throws Exception {

//        delete
        mock.perform(delete("/api/sleepMode/1"))
                .andExpect(status().isOk())
                .andExpect((content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))).andDo(print());
    }
}
