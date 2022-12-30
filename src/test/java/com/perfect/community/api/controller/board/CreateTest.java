package com.perfect.community.api.controller.board;

import com.perfect.community.api.dto.board.BoardDTO;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class CreateTest extends BoardControllerTest {

    String requestBody;

    @Test
    @WithAnonymousUser
    void createBoardWithAnonymous() throws Exception {
        try {
            requestBody = objectMapper.valueToTree(
                    BoardDTO.builder()
                            .title(utilsForTest.generateRandomTitle())
                            .comment("Creating a board for testing in JUNIT")
                            .build()
            ).toString();
            mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/api/board/create")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(requestBody))
                    .andExpect(status().isUnauthorized())
                    .andReturn();
            log.error(mvcResult.getResponse().getErrorMessage());
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    @Test
    @WithUserDetails("admin")
    void createBoardWithNullTitle() throws Exception {
        requestBody = objectMapper.valueToTree(
                BoardDTO.builder()
                        .title(null)
                        .comment("Creating a board for testing in JUNIT")
                        .build()
        ).toString();
        mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/api/board/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isBadRequest())
                .andReturn();
        log.error(mvcResult.getResponse().getContentAsString());
    }

    @Test
    @WithUserDetails("tester1")
    void createBoardWithNotAdmin() throws Exception {
        requestBody = objectMapper.valueToTree(
                BoardDTO.builder()
                        .title(utilsForTest.generateRandomTitle())
                        .comment("Creating a board for testing in JUNIT")
                        .build()
        ).toString();
        mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/api/board/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isUnauthorized())
                .andReturn();
        log.error(mvcResult.getResponse().getErrorMessage());
    }


    @Test
    @WithUserDetails("admin")
    void createBoard() throws Exception {
        requestBody = objectMapper.valueToTree(
                BoardDTO.builder()
                        .title(utilsForTest.generateRandomTitle())
                        .comment("Creating a board for testing in JUNIT")
                        .build()
        ).toString();
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/api/board/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk())
                .andReturn();
        long createdBoardNo = Long.parseLong(mvcResult.getResponse().getContentAsString());
        log.info(getBoardInfo(createdBoardNo));
    }

}