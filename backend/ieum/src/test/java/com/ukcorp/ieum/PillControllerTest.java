package com.ukcorp.ieum;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.ukcorp.ieum.pill.dto.request.PillInfoInsertRequestDto;
import com.ukcorp.ieum.pill.dto.request.PillInfoUpdateRequestDto;
import com.ukcorp.ieum.pill.dto.request.PillTimeInsertRequestDto;
import com.ukcorp.ieum.pill.dto.request.PillTimeUpdateRequestDto;
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
    requestDto.setCareNo(1L);
    requestDto.setPillName("이름");
    requestDto.setPillStartDate(LocalDate.parse("2024-01-30"));
    requestDto.setPillEndDate(LocalDate.parse("2024-02-29"));
    requestDto.setPillMethod("식전");
    requestDto.setPillDate("1111111");

    List<PillTimeInsertRequestDto> pillTimes = new ArrayList<>();
    PillTimeInsertRequestDto pillTime1 = new PillTimeInsertRequestDto();
    pillTime1.setPillTakeTime(90000L);
    pillTimes.add(pillTime1);

    PillTimeInsertRequestDto pillTime2 = new PillTimeInsertRequestDto();
    pillTime2.setPillTakeTime(140000L);
    pillTimes.add(pillTime2);

    requestDto.setPillTimes(pillTimes);

//    // JSON 변환
    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.registerModule(new JavaTimeModule());
    objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    String requestBody = objectMapper.writeValueAsString(requestDto);
//    String requestBody = "{\"care_no\": \"1\",\"pill_name\":\"이름\",\"pill_start_date\":\"2024-01-30\",\"pill_end_date\":\"2024-02-29\",\"pill_method\":\"식전\",\"pill_date\":\"1111111\",\"pill_times\":[{\"pill_take_time\":\"90000\"},{\"pill_take_time\":\"140000\"}]}";

    mock.perform(post("/api/pill")
                    .content(requestBody)
                    .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect((content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))).andDo(print());
  }

  @Test
  @DisplayName("약 정보 삭제")
  void testDelete() throws Exception {

    // 1에 해당하는 pillInfo 삭제
    mock.perform(delete("/api/pill/2"))
            .andExpect(status().isOk())
            .andExpect((content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))).andDo(print());
  }

  @Test
  @DisplayName("약 복용 시간 정보 삭제")
  void testTimeDelete() throws Exception {

    // 1에 해당하는 pillTime 삭제
    mock.perform(delete("/api/pill/time/4"))
            .andExpect(status().isOk())
            .andExpect((content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))).andDo(print());
  }


  @Test
  @DisplayName("약 정보 리스트 조회")
  void testSelectList() throws Exception {

    // careNo가 1인 사용자의 일정 리스트 조회
    mock.perform(get("/api/pill/1"))
            .andExpect(status().isOk())
            .andExpect((content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))).andDo(print());
  }

  @Test
  @DisplayName("약 정보 상세 조회")
  void testSelectDetail() throws Exception {
    // eventNo으로 해당 일정 상세 조회
    mock.perform(get("/api/pill/detail/3"))
            .andExpect(status().isOk())
            .andExpect((content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))).andDo(print());
  }


  @Test
  @DisplayName("일정 수정")
  void testUpdate() throws Exception {

    // 약 정보 등록 관련 테스트
    PillInfoUpdateRequestDto requestDto = new PillInfoUpdateRequestDto();
    requestDto.setPillInfoNo(1L);
    requestDto.setCareNo(1L);
    requestDto.setPillName("이름 수정");
    requestDto.setPillStartDate(LocalDate.parse("2024-01-30"));
    requestDto.setPillEndDate(LocalDate.parse("2024-02-29"));
    requestDto.setPillMethod("식후");
    requestDto.setPillDate("0000000");

    List<PillTimeUpdateRequestDto> pillTimes = new ArrayList<>();
    PillTimeUpdateRequestDto pillTime1 = new PillTimeUpdateRequestDto();
    pillTime1.setPillInfoNo(1L);
    pillTime1.setPillTakeTime(70000L);
    pillTime1.setPillTimeNo(1L);
    pillTimes.add(pillTime1);

    PillTimeUpdateRequestDto pillTime2 = new PillTimeUpdateRequestDto();
    pillTime2.setPillInfoNo(1L);
    pillTime2.setPillTakeTime(120000L);
    pillTime2.setPillTimeNo(2L);
    pillTimes.add(pillTime2);

    requestDto.setPillTimes(pillTimes);


//    // JSON 변환
    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.registerModule(new JavaTimeModule());
    objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    String requestBody = objectMapper.writeValueAsString(requestDto);

    mock.perform(put("/api/pill")
                    .content(requestBody)
                    .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect((content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))).andDo(print());
  }

}
