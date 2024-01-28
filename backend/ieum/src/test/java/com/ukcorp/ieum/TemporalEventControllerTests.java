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

    // 일정 등록 관련 테스트
    String requestBody = "{" +
            "\"care_no\": \"1\", " +
            "\"event_name\": \"경로당가기\"," +
            "\"event_date\": \"2024-01-15\"" +
            "}";

    mock.perform(post("/api/event")
                    .content(requestBody)
                    .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect((content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))).andDo(print());
  }

  @Test
  @DisplayName("일정 삭제")
  void testDelete() throws Exception {

    // 17에 해당하는 eventNo 일정 삭제
    mock.perform(delete("/api/event/1"))
            .andExpect(status().isOk())
            .andExpect((content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))).andDo(print());
  }


  @Test
  @DisplayName("일정 리스트 조회")
  void testSelect() throws Exception {
    
    // careNo이 17인 사용자의 일정 리스트 조회
    mock.perform(get("/api/event/1"))
            .andExpect(status().isOk())
            .andExpect((content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))).andDo(print());
  }

  @Test
  @DisplayName("일정 상세 조회")
  void testSelectDetail() throws Exception {
    // eventNo으로 해당 일정 상세 조회
    mock.perform(get("/api/event/detail/1"))
            .andExpect(status().isOk())
            .andExpect((content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))).andDo(print());
  }



  @Test
  @DisplayName("일정 수정")
  void testUpdate() throws Exception {

    String requestBody = "{\"event_no\": \"4\", " +
            "\"care_no\": \"1\", " +
            "\"event_name\": \"경로당22가기\"," +
            "\"event_date\": \"2024-01-15\"" +
            "}";

    mock.perform(put("/api/event")
                    .content(requestBody)
                    .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect((content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))).andDo(print());
  }



}