package com.perfect.community.api.controller.board;

import com.perfect.community.api.dto.board.BoardDTO;
import com.fasterxml.jackson.core.type.TypeReference;
import org.junit.jupiter.api.Test;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class ReadTest extends BoardControllerTest {

    @Test
    @WithAnonymousUser
    void getBoardList() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/api/board/list"))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();
        List<BoardDTO> boardDTOList = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), new TypeReference<List<BoardDTO>>(){});
        log.info(boardDTOList);
    }

    @Test
    @WithAnonymousUser
    void getBoardInfo() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/api/board/info/1"))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();
        log.info(objectMapper.readValue(mvcResult.getResponse().getContentAsString(), BoardDTO.class));
    }

    @Test
    @WithAnonymousUser
    void getBoardInfoWithInvalidBoardNo() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/api/board/info/-1"))
                .andExpect(status().isBadRequest())
                .andDo(print())
                .andReturn();
        log.error(mvcResult.getResponse().getContentAsString());
    }
}