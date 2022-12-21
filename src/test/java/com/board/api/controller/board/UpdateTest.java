package com.board.api.controller.board;

import com.board.api.dto.board.BoardDTO;
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

    @Test
    @WithUserDetails("admin")
    void updateBoard() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        String requestBody = objectMapper.valueToTree(
                BoardDTO.builder()
                        .title("Updating")
                        .comment("Updating a board for testing in JUNIT")
                        .build()
        ).toString();
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.put("/api/board/update/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        log.info(mvcResult.getResponse().getContentAsString());
    }

    @Test
    @WithUserDetails("admin")
    void updateBoardWithInvalidBoardNo() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        String requestBody = objectMapper.valueToTree(
                BoardDTO.builder()
                        .title("Updating")
                        .comment("Updating a board for testing in JUNIT")
                        .build()
        ).toString();
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.put("/api/board/update/100")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andReturn();
        log.error(mvcResult.getResponse().getContentAsString());
    }

    @Test
    @WithUserDetails("admin")
    void updateBoardWithNullTitle() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String requestBody = objectMapper.valueToTree(
                    BoardDTO.builder()
                            .title(null)
                            .comment("Updating a board for testing in JUNIT")
                            .build()
            ).toString();
            MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.put("/api/board/update/1")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(requestBody))
                    .andDo(print())
                    .andExpect(status().isBadRequest())
                    .andReturn();
            log.error(mvcResult.getResponse().getContentAsString());
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }
    }

    @Test
    @WithUserDetails("tester1")
    void updateBoardWithUnauthorizedUser() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        String requestBody = objectMapper.valueToTree(
                BoardDTO.builder()
                        .title("Updating")
                        .comment("Updating a board for testing in JUNIT")
                        .build()
        ).toString();
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.put("/api/board/update/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andDo(print())
                .andExpect(status().isUnauthorized())
                .andReturn();
        log.error(mvcResult.getResponse().getContentAsString());
    }

    @Test
    @WithAnonymousUser
    void updateBoardWithAnonymous() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        String requestBody = objectMapper.valueToTree(
                BoardDTO.builder()
                        .title("Updating")
                        .comment("Updating a board for testing in JUNIT")
                        .build()
        ).toString();
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.put("/api/board/update/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andDo(print())
                .andExpect(status().isUnauthorized())
                .andReturn();
        log.error(mvcResult.getResponse().getContentAsString());
    }
}