package com.perfect.community.api.controller.board;

import com.fasterxml.jackson.core.type.TypeReference;
import com.perfect.community.api.dto.board.BoardDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("[Board's Read]")
class ReadTest extends BoardControllerTest {

    @Test
    @DisplayName("[Board's Read] - Get List")
    @WithAnonymousUser
    void getBoardList() throws Exception {
        mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/api/board"))
                .andExpect(status().isOk())
                .andReturn();
        List<BoardDTO> boardDTOList = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), new TypeReference<List<BoardDTO>>(){});
        log.info(boardDTOList);
    }

    @Test
    @DisplayName("[Board's Read] - Get board's details")
    @WithAnonymousUser
    void getBoardInfo() throws Exception {
        log.info(getBoardInfo(1));
    }

    @Test
    @DisplayName("[Board's Read] - Get board's details by invalid board number")
    @WithAnonymousUser
    void getBoardInfoWithInvalidBoardNo() throws Exception {
        mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/api/board/-1"))
                .andExpect(status().isOk())
                .andReturn();
    }
}