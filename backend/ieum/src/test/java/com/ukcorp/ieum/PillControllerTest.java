package com.ukcorp.ieum;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ukcorp.ieum.pill.dto.request.PillInfoInsertRequestDto;
import com.ukcorp.ieum.pill.dto.request.PillTimeInsertRequestDto;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Slf4j
@AutoConfigureMockMvc
public class PillControllerTest {

  @Autowired
  private MockMvc mock;

  @Test
  void build() {
    log.debug("Success Build");
  }

  @Test
  @DisplayName("약 정보 등록")
  void testRegist() throws Exception {

    // 약 정보 등록 관련 테스트
    PillInfoInsertRequestDto requestDto = new PillInfoInsertRequestDto();
    requestDto.setCareNo(1234L);
    requestDto.setPillName("이름");
    requestDto.setStartDate(LocalDate.parse("2024-01-30"));
    requestDto.setEndDate(LocalDate.parse("2024-02-29"));
    requestDto.setPillMethod("식전");
    requestDto.setPillDate("1111111");

    List<PillTimeInsertRequestDto> pillTimes = new ArrayList<>();
    PillTimeInsertRequestDto pillTime1 = new PillTimeInsertRequestDto();
    pillTime1.setPillInfoNo(1L);
    pillTime1.setPillTakeTime(90000L);
    pillTimes.add(pillTime1);

    PillTimeInsertRequestDto pillTime2 = new PillTimeInsertRequestDto();
    pillTime2.setPillInfoNo(1L);
    pillTime2.setPillTakeTime(140000L);
    pillTimes.add(pillTime2);

    requestDto.setPillTimes(pillTimes);

    // JSON 변환
    ObjectMapper objectMapper = new ObjectMapper();
    String requestBody = objectMapper.writeValueAsString(requestDto);

    mock.perform(post("/api/pill")
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
