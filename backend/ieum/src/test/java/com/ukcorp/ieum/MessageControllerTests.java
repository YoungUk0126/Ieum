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
class MessageControllerTests {

  @Autowired
  private MockMvc mock;

  @Test
  void build() {
    log.debug("Success Build");
  }

  @Test
  @DisplayName("메세지 등록")
  void testRegist() throws Exception {

    // 메세지 등록 관련 테스트
    String requestBody = "{" +
            "\"care_no\": \"1\", " +
            "\"message_name\": \"sds.mp3\"," +
            "\"message_type\": \"video\"," +
            "\"message_time\": \"2024-01-15\"," +
            "\"message_people\": \"딸\"" +
            "}";

    mock.perform(post("/api/message")
                    .content(requestBody)
                    .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect((content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))).andDo(print());
  }

  @Test
  @DisplayName("메세지 삭제")
  void testDelete() throws Exception {

    // 1에 해당하는 messageNo 메세지 삭제
    mock.perform(delete("/api/message/1"))
            .andExpect(status().isOk())
            .andExpect((content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))).andDo(print());
  }


  @Test
  @DisplayName("메세지 리스트 조회")
  void testSelect() throws Exception {

    // careNo이 1인 사용자의 메세지 리스트 조회
    mock.perform(get("/api/message/1"))
            .andExpect(status().isOk())
            .andExpect((content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))).andDo(print());
  }

  @Test
  @DisplayName("메세지 상세 조회")
  void testSelectDetail() throws Exception {
    // messageNo으로 해당 메세지 상세 조회
    mock.perform(get("/api/message/detail/1"))
            .andExpect(status().isOk())
            .andExpect((content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))).andDo(print());
  }


  @Test
  @DisplayName("메세지 수정")
  void testUpdate() throws Exception {

    String requestBody = "{" +
            "\"care_no\": \"1\", " +
            "\"message_name\": \"update.mp3\"," +
            "\"message_type\": \"video\"," +
            "\"message_time\": \"2024-01-15\"," +
            "\"message_people\": \"딸\"" +
            "}";

    mock.perform(put("/api/message")
                    .content(requestBody)
                    .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect((content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))).andDo(print());
  }


}