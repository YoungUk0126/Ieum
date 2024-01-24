package com.ukcorp.ieum;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Slf4j
@AutoConfigureMockMvc
public class CareControllerTests {

    @Autowired
    private MockMvc mock;


    @Test
    void build() {
        log.debug("Success Build");
    }

    @Test
    @DisplayName("피보호자 정보 조회")
    void testSelect() throws Exception {

//        get
        mock.perform(get("/api/care/1"))
                .andExpect(status().isOk())
                .andExpect((content().contentType(MediaType.APPLICATION_JSON))).andDo(print());
    }

}
