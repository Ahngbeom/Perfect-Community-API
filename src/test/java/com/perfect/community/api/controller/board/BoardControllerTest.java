package com.perfect.community.api.controller.board;

import com.perfect.community.api.controller.ControllerIntegrationTest;
import com.perfect.community.api.dto.board.BoardDTO;
import com.perfect.community.api.service.utils.RelocateService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.transaction.AfterTransaction;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.io.UnsupportedEncodingException;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class BoardControllerTest extends ControllerIntegrationTest {

    @Autowired
    protected BoardController controller;

    @BeforeEach
    void setUp() {
        setUp(controller);
    }

    @AfterTransaction
    void relocateBoardNumbers() {
        log.info("auto_increment key value: " + relocateService.relocateBoardNumbers("boards"));
    }

    public BoardDTO getBoardInfo(long bno) throws Exception {
        mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/api/board/info/" + bno))
                .andExpect(status().isOk())
                .andReturn();
        return objectMapper.readValue(mvcResult.getResponse().getContentAsString(), BoardDTO.class);
    }

}
