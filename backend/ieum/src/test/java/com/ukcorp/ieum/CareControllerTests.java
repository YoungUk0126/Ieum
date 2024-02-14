package com.ukcorp.ieum;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ukcorp.ieum.care.controller.CareController;
import com.ukcorp.ieum.care.dto.request.CareUpdateRequestDto;
import com.ukcorp.ieum.care.dto.response.CareGetResponseDto;
import com.ukcorp.ieum.care.service.CareServiceImpl;
import com.ukcorp.ieum.config.SecurityTestConfig;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.mock.web.MockPart;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.charset.StandardCharsets;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(CareController.class)
@ExtendWith(MockitoExtension.class)
@Import(SecurityTestConfig.class)
@Slf4j
public class CareControllerTests {

    @Autowired
    private MockMvc mock;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private CareServiceImpl careService;

    @Test
    void build() {
        log.debug("Success Build");
    }

    @Test
    @DisplayName("피보호자 정보 조회")
    @WithMockUser(username = "test123", authorities = {"ROLE_USER"})
    void testSelect() throws Exception {
        // given
        CareGetResponseDto response = CareGetResponseDto.builder().build();

        when(careService.getCareInfo()).thenReturn(response);

        // get
        mock.perform(get("/api/care"))
                .andExpect(status().isOk())
                .andExpect((content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON)))
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data.careName").value(response.getCareName()))
                .andExpect(jsonPath("$.data.careGender").value(response.getCareGender()))
                .andExpect(jsonPath("$.data.careBirth").value(response.getCareBirth()))
                .andExpect(jsonPath("$.data.carePhone").value(response.getCarePhone()))
                .andExpect(jsonPath("$.data.careAddr").value(response.getCareAddr()))
                .andExpect(jsonPath("$.data.careImage").value(response.getCareImage()))
                .andExpect(jsonPath("$.data.careSerial").value(response.getCareSerial()))
                .andDo(print());
    }

    @Test
    @DisplayName("피보호자 정보 수정")
    @WithMockUser(username = "test123", authorities = {"ROLE_USER"})
    void testUpdate() throws Exception {
        CareUpdateRequestDto requestDto = CareUpdateRequestDto.builder().build();
        String requestBody = objectMapper.writeValueAsString(requestDto);
        MockMultipartFile dto = new MockMultipartFile("data", "careDto",
                "application/json", requestBody.getBytes(StandardCharsets.UTF_8));

        MockMultipartFile image = new MockMultipartFile("file",
                "test.jpg", "text/plain", "test file".getBytes(StandardCharsets.UTF_8) );

        // doNothing() 대신 예상되는 메서드 호출을 지정
        doNothing().when(careService).updateCareInfo(any(CareUpdateRequestDto.class));

        mock.perform(multipart(HttpMethod.PUT, "/api/care")
                        .file(dto)
                        .file(image)
                        .part(new MockPart("data", requestBody.getBytes(StandardCharsets.UTF_8)))
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk());
    }



    @Test
    @DisplayName("피보호자 정보 삭제")
    @WithMockUser(username = "test123", authorities = {"ROLE_USER"})
    void testDelete() throws Exception {
        doNothing().when(careService).deleteCareInfo();

        // delete
        mock.perform(delete("/api/care"))
                .andExpect(status().isOk())
                .andExpect((content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON)))
                .andDo(print());
    }

}
