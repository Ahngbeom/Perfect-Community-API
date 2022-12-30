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

    @Autowired
    protected RelocateService relocateService;

    @BeforeEach
    void setUp() {
        setUp(controller);
    }

    @AfterEach
    void checkMvcResult() throws UnsupportedEncodingException {
        HttpStatus status = HttpStatus.resolve(mvcResult.getResponse().getStatus());
        assert status != null;
        if (status.is2xxSuccessful()) {
            log.info("[" + status + "] ResponseBody: " + mvcResult.getResponse().getContentAsString());
        } else if (status.is3xxRedirection()) {
            log.warn("[" + status + "] ResponseBody: " + mvcResult.getResponse().getContentAsString()
                    + "\n[" + status + "] Servlet Error: " + mvcResult.getResponse().getErrorMessage());
        } else {
            log.error("[" + status + "] ResponseBody: " + mvcResult.getResponse().getContentAsString()
                    + "\n [" + status + "] Servlet Error: " + mvcResult.getResponse().getErrorMessage());
        }
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
