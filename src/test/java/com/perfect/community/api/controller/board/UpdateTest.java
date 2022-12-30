package com.perfect.community.api.controller.board;

import com.perfect.community.api.dto.board.BoardDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class UpdateTest extends BoardControllerTest {

    String requestBody;

    @Test
    @WithUserDetails("admin")
    void updateBoard() throws Exception {
        requestBody = objectMapper.valueToTree(
                BoardDTO.builder()
                        .title("Updating")
                        .comment("Updating a board for testing in JUNIT")
                        .build()
        ).toString();
        mvcResult = mockMvc.perform(MockMvcRequestBuilders.put("/api/board/update/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk())
                .andReturn();
        log.info(getBoardInfo(Long.parseLong(mvcResult.getResponse().getContentAsString())));
    }

    @Test
    @WithUserDetails("admin")
    void updateBoardWithInvalidBoardNo() throws Exception {
        requestBody = objectMapper.valueToTree(
                BoardDTO.builder()
                        .title("Updating")
                        .comment("Updating a board for testing in JUNIT")
                        .build()
        ).toString();
        mvcResult = mockMvc.perform(MockMvcRequestBuilders.put("/api/board/update/100")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isBadRequest())
                .andReturn();
    }

    @Test
    @WithUserDetails("admin")
    void updateBoardWithNullTitle() {
        try {
            requestBody = objectMapper.valueToTree(
                    BoardDTO.builder()
                            .title(null)
                            .comment("Updating a board for testing in JUNIT")
                            .build()
            ).toString();
            mvcResult = mockMvc.perform(MockMvcRequestBuilders.put("/api/board/update/1")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(requestBody))
                    .andExpect(status().isBadRequest())
                    .andReturn();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @WithUserDetails("tester1")
    void updateBoardWithUnauthorizedUser() throws Exception {
        requestBody = objectMapper.valueToTree(
                BoardDTO.builder()
                        .title("Updating")
                        .comment("Updating a board for testing in JUNIT")
                        .build()
        ).toString();
        mvcResult = mockMvc.perform(MockMvcRequestBuilders.put("/api/board/update/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isUnauthorized())
                .andReturn();
    }

    @Test
    @WithAnonymousUser
    void updateBoardWithAnonymous() throws Exception {
        requestBody = objectMapper.valueToTree(
                BoardDTO.builder()
                        .title("Updating")
                        .comment("Updating a board for testing in JUNIT")
                        .build()
        ).toString();
        mvcResult = mockMvc.perform(MockMvcRequestBuilders.put("/api/board/update/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isUnauthorized())
                .andReturn();
    }
}