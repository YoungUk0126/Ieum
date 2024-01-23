package com.ukcorp.ieum;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Slf4j
@AutoConfigureMockMvc
class TemporalEventControllerTests {

  @Autowired
  private MockMvc mock;

  @Test
  void build() {
    log.debug("Success Build");
  }

  @Test
  @DisplayName("일정 등록")
  void testRegist() throws Exception {

    String requestBody = "{\"eventNo\": \"18\", " +
            "\"careNo\": \"17\", " +
            "\"eventName\": \"경로당가기\"" +
            "\"eventDate\": \"2024-01-15\"" +
            "}";

    mock.perform(put("/api/event")
                    .content(requestBody)
                    .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect((content().contentType(MediaType.APPLICATION_JSON))).andDo(print());
  }

  @Test
  @DisplayName("일정 삭제")
  void testDelete() throws Exception {

    mock.perform(delete("/api/event/17"))
            .andExpect(status().isOk())
            .andExpect((content().contentType(MediaType.APPLICATION_JSON))).andDo(print());
  }


  @Test
  @DisplayName("일정 리스트 조회")
  void testSelect() throws Exception {

    mock.perform(get("/api/event/17"))
            .andExpect(status().isOk())
            .andExpect((content().contentType(MediaType.APPLICATION_JSON))).andDo(print());
  }

  @Test
  @DisplayName("일정 상세 조회")
  void testSelectDetail() throws Exception {

    MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
    map.add("eventNo", "18");

    mock.perform(get("/api/event/detail").params(map))
            .andExpect(status().isOk())
            .andExpect((content().contentType(MediaType.APPLICATION_JSON))).andDo(print());
  }



  @Test
  @DisplayName("일정 수정")
  void testUpdate() throws Exception {

    String requestBody = "{\"eventNo\": \"18\", " +
                           "\"careNo\": \"17\", " +
                          "\"eventName\": \"경로당가기\"" +
                          "\"eventDate\": \"2024-01-15\"" +
                         "}";

    mock.perform(post("/api/event")
                    .content(requestBody)
                    .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect((content().contentType(MediaType.APPLICATION_JSON))).andDo(print());
  }



}